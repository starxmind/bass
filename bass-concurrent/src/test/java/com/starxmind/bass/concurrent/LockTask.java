package com.starxmind.bass.concurrent;

import com.starxmind.bass.concurrent.lock.memory.MemoryXLock;
import com.starxmind.bass.concurrent.lock.memory.MemoryXLockFactory;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTask {
    private final MemoryXLockFactory memoryXLockFactory = new MemoryXLockFactory();
    private final AtomicInteger count = new AtomicInteger(10);
    private final String lockKey = "funcTest";

    public void execute() {
        MemoryXLock memoryXLock = memoryXLockFactory.get(lockKey);
        memoryXLock.lock();
        int after = count.addAndGet(2);
        System.out.printf("%s [%s] after: %s%n", LocalDateTime.now(), Thread.currentThread().getName(), after);
        System.out.println("FuncTest lock status: " + isLocked());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        memoryXLock.unlock();
    }

    public boolean isLocked() {
        MemoryXLock memoryXLock = memoryXLockFactory.get(lockKey);
        return memoryXLock.isLocked();
    }
}
