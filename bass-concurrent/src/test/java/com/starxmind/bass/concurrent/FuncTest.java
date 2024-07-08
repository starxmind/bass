package com.starxmind.bass.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class FuncTest {
    private final MemoryLock memoryLock = new MemoryLock();
    private final AtomicInteger count = new AtomicInteger(10);
    private final String lockKey = "funcTest";

    public void execute() {
        memoryLock.lock(lockKey);
        int after = count.addAndGet(2);
        System.out.printf("%s [%s] after: %s%n", LocalDateTime.now(), Thread.currentThread().getName(), after);
        System.out.println("FuncTest lock status: " + isLocked());
        memoryLock.unlock(lockKey);
    }

    public boolean isLocked() {
        return memoryLock.isLocked(lockKey);
    }
}
