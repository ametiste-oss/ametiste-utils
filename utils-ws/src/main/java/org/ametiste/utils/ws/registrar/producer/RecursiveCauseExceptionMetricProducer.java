package org.ametiste.utils.ws.registrar.producer;

import org.ametiste.metrics.MetricsService;
import org.ametiste.utils.ws.registrar.ErrorMetricProducer;

/**
 * Created by ametiste on 1/27/16.
 */
public class RecursiveCauseExceptionMetricProducer implements ErrorMetricProducer {

    private Class<? extends Throwable> applicableException;
    private String metricName;

    public RecursiveCauseExceptionMetricProducer(Class<? extends Throwable> applicableException, String metricName) {
        this.applicableException = applicableException;
        this.metricName = metricName;
    }


    @Override
    public void produceIfApplicable(MetricsService service, Exception e) {

        if(applicable(e)) {
            service.increment(metricName, 1);
        }
    }

    private boolean applicable(Throwable e) {
        return e.getClass().equals(applicableException) || e.getCause() != null && applicable(e.getCause());
        //сама в шоке
    }
}
