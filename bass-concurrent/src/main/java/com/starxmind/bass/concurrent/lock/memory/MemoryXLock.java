package com.starxmind.bass.concurrent.lock.memory;

import com.starxmind.bass.concurrent.lock.XLock;
import com.starxmind.bass.concurrent.lock.exceptions.LockException;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Memory lock
 * @author pizzalord
 * @since 1.o
 */
@RequiredArgsConstructor
public class MemoryXLock implements XLock {

    private final ReentrantLock lock;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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

    @Override
    public void lock(long leaseTime, TimeUnit timeUnit) {
        scheduler.schedule(this::unlock, leaseTime, timeUnit);
        lock.lock();
    }

    @Override
    public boolean tryLock(long waitTime, long leaseTime, TimeUnit timeUnit) {
        try {
            if (lock.tryLock(waitTime, timeUnit)) {
                scheduler.schedule(this::unlock, leaseTime, timeUnit);
                return true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LockException(String.format("Acquire lock fail by thread interrupted,path:%s", lock), e);
        }
        return false;
    }
}
