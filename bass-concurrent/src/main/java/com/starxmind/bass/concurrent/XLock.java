package com.starxmind.bass.concurrent;

public interface XLock {
    boolean isLocked(String lockKey);

    boolean isHeldByCurrentThread(String lockKey);

    void lock(String lockKey);

    boolean tryLock(String lockKey);

    void unlock(String lockKey);
}
