package com.starxmind.bass.concurrent;

import com.starxmind.bass.concurrent.utils.KeyUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class MemoryLockFactory {

    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public MemoryXLock get(String lockName) {
        final String lockKey = KeyUtils.lockKey(lockName);
        ReentrantLock nativeLock = lockMap.computeIfAbsent(lockKey, k -> new ReentrantLock());
        return new MemoryXLock(nativeLock);
    }

}
