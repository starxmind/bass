package com.starxmind.bass.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryLock implements XLock {

    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    @Override
    public boolean isLocked(String lockKey) {
        ReentrantLock lock = lockMap.get(lockKey);
        return lock != null && lock.isLocked();
    }

    @Override
    public boolean isHeldByCurrentThread(String lockKey) {
        ReentrantLock lock = lockMap.get(lockKey);
        return lock != null && lock.isHeldByCurrentThread();
    }

    @Override
    public void lock(String lockKey) {
        ReentrantLock lock = lockMap.computeIfAbsent(lockKey, k -> new ReentrantLock());
        lock.lock();
    }

    @Override
    public boolean tryLock(String lockKey) {
        ReentrantLock lock = lockMap.computeIfAbsent(lockKey, k -> new ReentrantLock());
        return lock.tryLock();
    }

    @Override
    public void unlock(String lockKey) {
        ReentrantLock lock = lockMap.get(lockKey);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
            if (!lock.isLocked()) {
                lockMap.remove(lockKey);
            }
        }
    }

}
