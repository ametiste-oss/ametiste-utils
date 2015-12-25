package org.ametiste.utils.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class SimpleExternalServiceReader<T> implements ExternalServiceReader {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RestTemplate restTemplate;
	private final String sourceURI;

	private final Class<T> classType;

	public SimpleExternalServiceReader(RestTemplate restTemplate, String sourceURI, Class<T> classType) {
		Assert.notNull(restTemplate, "restTemplate must be not null.");
		Assert.hasText(sourceURI, "sourceURI must be not null.");

		this.classType = classType;
		this.restTemplate = restTemplate;
		this.sourceURI = sourceURI;
	}

	public T getSource(Object... ids) {

		T result;
		try {
			result = restTemplate.getForObject(sourceURI, classType, ids);
		} catch (Exception e) {

			if (logger.isErrorEnabled())
				logger.error("External Source quering error: ", e);
			throw new ExternalServiceRuntimeException("External Source quering error: ", e);
		}

		return result;
	}

}
