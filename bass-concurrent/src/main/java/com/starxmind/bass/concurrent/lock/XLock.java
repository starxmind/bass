package com.starxmind.bass.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * Lock
 *
 * @author pizzalord
 * @since 1.0
 */
public interface XLock {
    boolean isLocked();

    boolean isHeldByCurrentThread();

    void lock();

    boolean tryLock();

    boolean tryLock(long waitTime, TimeUnit timeUnit);

    void unlock();

    void lock(long leaseTime, TimeUnit timeUnit);

    boolean tryLock(long waitTime, long leaseTime, TimeUnit timeUnit);
}
