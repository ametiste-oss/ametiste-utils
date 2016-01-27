package org.ametiste.utils.ws.registrar;

import org.ametiste.metrics.mock.MockMetricsService;
import org.ametiste.utils.ws.registrar.producer.RecursiveCauseExceptionMetricProducer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ametiste on 1/27/16.
 */
public class MetricExceptionRegistrarTest {

    private MetricExceptionRegistrar registrar;
    private MockMetricsService service = new MockMetricsService();

    private RecursiveCauseExceptionMetricProducer first =
            new RecursiveCauseExceptionMetricProducer(NullPointerException.class, "nullErrors");
    private RecursiveCauseExceptionMetricProducer second =
            new RecursiveCauseExceptionMetricProducer(IllegalArgumentException.class, "illegalErrors");


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service.resetData();
        registrar = new MetricExceptionRegistrar(service, Arrays.asList(first, second));
    }

    @Test
    public void testJournalize() throws Exception {
        registrar.journalize(new NullPointerException());
        service.verify("nullErrors").registered().increment().increment(1);
        service.verify("illegalErrors").notRegistered();
    }

    @Test
    public void testJournalizeCause() throws Exception {
        registrar.journalize(new RuntimeException(new NullPointerException()));
        service.verify("nullErrors").registered().increment().increment(1);
        service.verify("illegalErrors").notRegistered();
    }

    @Test
    public void testJournalizeBoth() throws Exception {
        registrar.journalize(new IllegalArgumentException(new NullPointerException()));
        service.verify("nullErrors").registered().increment().increment(1);
        service.verify("illegalErrors").registered().increment().increment(1);
    }

    @Test
    public void testJournalizeNone() throws Exception {
        registrar.journalize(new ArithmeticException());
        service.verify("nullErrors").notRegistered();
        service.verify("illegalErrors").notRegistered();
    }
}