package com.starxmind.bass.datastructure.map;

import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

public class ExpiringMap<K, V> {
    private final Map<K, V> valueMap = new ConcurrentHashMap<>();
    private final Map<K, ScheduledFuture<?>> expirationMap = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public ExpiringMap() {
    }

    /**
     * Not expired
     */
    public void put(K key, V value) {
        put(key, value, -1, null);
    }

    public void put(K key, V value, long expireAfter, TimeUnit timeUnit) {
        if (expireAfter != -1) {
            // 移除之前的任务
            ScheduledFuture<?> existingExpiration = expirationMap.remove(key);
            if (existingExpiration != null) {
                existingExpiration.cancel(false);
            }
            ScheduledFuture<?> expiration = executor.schedule(() -> evict(key), expireAfter, timeUnit);
            expirationMap.put(key, expiration);
        }
        valueMap.put(key, value);
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return computeIfAbsent(key, mappingFunction, -1, null);
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction, long expireAfter, TimeUnit timeUnit) {
        if (expireAfter != -1) {
            expirationMap.computeIfAbsent(key, k -> executor.schedule(() -> evict(key), expireAfter, timeUnit));
        }
        return valueMap.computeIfAbsent(key, mappingFunction);
    }

    public V get(K key) {
        return valueMap.get(key);
    }

    public boolean containsKey(K key) {
        return valueMap.containsKey(key);
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
