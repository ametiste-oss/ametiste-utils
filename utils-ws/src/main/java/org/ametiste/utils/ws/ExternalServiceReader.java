package org.ametiste.utils.ws;

public interface ExternalServiceReader<T> {

	T getSource(Object... ids);

}
