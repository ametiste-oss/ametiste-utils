package org.ametiste.utils.ws.registrar;

import org.ametiste.metrics.MetricsService;

import java.util.List;

/**
 * Created by ametiste on 1/27/16.
 */
public class MetricExceptionRegistrar implements ExceptionRegistrar {

    private MetricsService service;
    private List<ErrorMetricProducer> metricProducers;

    public MetricExceptionRegistrar(MetricsService service, List<ErrorMetricProducer> metricProducers) {
        this.service = service;
        this.metricProducers = metricProducers;
    }

    @Override
    public void journalize(Exception e) {
        metricProducers.forEach(p -> p.produceIfApplicable(service, e));
    }
}
