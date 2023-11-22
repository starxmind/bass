package com.starxmind.bass.datastructure.tree;

import java.util.Map;
import java.util.concurrent.*;

public class ExpiringMap<K, V> {
    private final Map<K, ScheduledFuture<?>> expirationMap = new ConcurrentHashMap<>();
    private final Map<K, V> valueMap = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public ExpiringMap() {
    }

    public void put(K key, V value, long expireAfterMillis) {
        // 移除之前的任务
        ScheduledFuture<?> existingExpiration = expirationMap.remove(key);
        if (existingExpiration != null) {
            existingExpiration.cancel(false);
        }

        ScheduledFuture<?> expiration = executor.schedule(() -> evict(key), expireAfterMillis, TimeUnit.MILLISECONDS);
        expirationMap.put(key, expiration);
        valueMap.put(key, value);
    }

    public V get(K key) {
        return valueMap.get(key);
    }

    public V remove(K key) {
        ScheduledFuture<?> expiration = expirationMap.remove(key);
        if (expiration != null) {
            expiration.cancel(false);
        }
        return valueMap.remove(key);
    }

    private void evict(K key) {
        expirationMap.remove(key);
        valueMap.remove(key);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
