package com.starxmind.bass.concurrent.lock.memory;

import com.starxmind.bass.concurrent.lock.XLockFactory;
import com.starxmind.bass.concurrent.lock.utils.KeyUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class MemoryXLockFactory implements XLockFactory {

    private final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    @Override
    public MemoryXLock get(String lockName) {
        final String lockKey = KeyUtils.lockKey(lockName);
        ReentrantLock nativeLock = lockMap.computeIfAbsent(lockKey, k -> new ReentrantLock());
        return new MemoryXLock(nativeLock);
    }

}
