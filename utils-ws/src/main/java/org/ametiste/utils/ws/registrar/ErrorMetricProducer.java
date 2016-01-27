package org.ametiste.utils.ws.registrar;

import org.ametiste.metrics.MetricsService;

/**
 * Created by ametiste on 1/27/16.
 */
public interface ErrorMetricProducer {

    void produceIfApplicable(MetricsService service, Exception e);
}
