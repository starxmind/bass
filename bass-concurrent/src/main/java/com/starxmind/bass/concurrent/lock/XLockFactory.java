package com.starxmind.bass.concurrent.lock;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public interface XLockFactory {
    XLock get(String lockName);
}
