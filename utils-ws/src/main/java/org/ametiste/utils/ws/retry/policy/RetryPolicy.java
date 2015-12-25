package org.ametiste.utils.ws.retry.policy;

/**
 * Created by ametiste on 7/24/15.
 */
public interface RetryPolicy {

    boolean delayRequired();

    long getRetryDelay();

    int getRetryNumber();

}
