package org.ametiste.utils.ws.retry;

import org.ametiste.utils.ws.ExternalServiceReader;
import org.ametiste.utils.ws.ExternalServiceRuntimeException;
import org.ametiste.utils.ws.retry.policy.RetryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class RetryExternalServiceReader<T> implements ExternalServiceReader {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RestTemplate restTemplate;
	private final String sourceURI;

	private final Class<T> classType;
	private RetryPolicy policy;

	public RetryExternalServiceReader(RestTemplate restTemplate, RetryPolicy policy, String sourceURI, Class<T> classType) {
		this.policy = policy;
		Assert.notNull(restTemplate, "restTemplate must be not null.");
		Assert.hasText(sourceURI, "sourceURI must be not null.");

		this.classType = classType;
		this.restTemplate = restTemplate;
		this.sourceURI = sourceURI;
	}

	public T getSource(Object... params) {

		try {
			return withRetries(policy.getRetryNumber(), params);
		} catch (Exception e) {

			if (logger.isDebugEnabled()) {
                logger.debug("External Source querying error: ", e);
            }
			throw new ExternalServiceRuntimeException("External Source querying error: ", e);
		}

	}

	private T withRetries(int retriesLeft, Object... params) {

		try {
			return restTemplate.getForObject(sourceURI, classType, params);
		}catch (Exception e) {
			if(retriesLeft==0) {
				throw e;
			}
			if(policy.getRetryDelay()!=0) {
				try {
					Thread.sleep(policy.getRetryDelay());
				} catch (InterruptedException e1) {
					//do nothing
				}
			}
			return withRetries(retriesLeft-1, params);
		}

	}

}
