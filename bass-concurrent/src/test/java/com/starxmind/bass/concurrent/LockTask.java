package com.starxmind.bass.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTask {
    private final MemoryLockFactory memoryLockFactory = new MemoryLockFactory();
    private final AtomicInteger count = new AtomicInteger(10);
    private final String lockKey = "funcTest";

    public void execute() {
        MemoryXLock memoryXLock = memoryLockFactory.get(lockKey);
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
        MemoryXLock memoryXLock = memoryLockFactory.get(lockKey);
        return memoryXLock.isLocked();
    }
}
