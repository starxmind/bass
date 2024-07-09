package com.starxmind.bass.concurrent.lock;

import java.util.concurrent.TimeUnit;

public interface LeaseXLock extends XLock {
    void lock(long leaseTime, TimeUnit timeUnit);

    boolean tryLock(long waitTime, long leaseTime, TimeUnit timeUnit);
}
