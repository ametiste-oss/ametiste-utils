package org.ametiste.utils.ws;

import org.ametiste.utils.ws.registrar.ExceptionRegistrar;

/**
 * Created by ametiste on 1/27/16.
 */
public class MeasurableExternalServiceReader<T> implements ExternalServiceReader<T> {


    private ExternalServiceReader<T> reader;
    private ExceptionRegistrar registrar;

    public MeasurableExternalServiceReader(ExternalServiceReader<T> reader, ExceptionRegistrar registrar) {
        this.reader = reader;
        this.registrar = registrar;
    }

    @Override
    public T getSource(Object... ids) {
        try {
            return reader.getSource(ids);
        } catch (ExternalServiceRuntimeException e) {
            registrar.journalize(e);
            throw e;
        }
    }
}
