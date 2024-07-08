package com.starxmind.bass.concurrent;

import java.util.concurrent.TimeUnit;

public interface XLock {
    boolean isLock(String lockKey);

    boolean isHeldByCurrentThread(String lockKey);

    void lock(String lockKey, long leaseTime, TimeUnit unit);

    boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit);

    void unlock(String lockKey);
}
