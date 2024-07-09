package com.starxmind.bass.concurrent;

import java.util.concurrent.TimeUnit;

public interface XLock {
    boolean isLocked();

    boolean isHeldByCurrentThread();

    void lock();

    boolean tryLock();

    boolean tryLock(long waitTime, TimeUnit timeUnit);

    void unlock();
}
