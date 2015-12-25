package org.ametiste.utils.ws.retry.policy;

/**
 * Created by ametiste on 7/24/15.
 */
public class NoDelayRetryPolicy implements RetryPolicy {

    private int retryNumber;

    public NoDelayRetryPolicy(int retryNumber) {
        this.retryNumber = retryNumber;
    }

    @Override
    public boolean delayRequired() {
        return false;
    }

    @Override
    public long getRetryDelay() {
        return 0;
    }

    @Override
    public int getRetryNumber() {
        return retryNumber;
    }
}
