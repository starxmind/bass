package com.starxmind.bass.concurrent;

import com.starxmind.bass.concurrent.exceptions.LockException;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class MemoryXLock implements XLock {

    private final ReentrantLock lock;

    @Override
    public boolean isLocked() {
        return lock != null && lock.isLocked();
    }

    @Override
    public boolean isHeldByCurrentThread() {
        return lock != null && lock.isHeldByCurrentThread();
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public boolean tryLock() {
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(long waitTime, TimeUnit timeUnit) {
        try {
            return lock.tryLock(waitTime, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LockException(String.format("Acquire lock fail by thread interrupted,path:%s", lock), e);
        }
    }

    @Override
    public void unlock() {
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
