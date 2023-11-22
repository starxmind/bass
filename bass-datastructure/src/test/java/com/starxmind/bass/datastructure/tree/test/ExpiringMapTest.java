package com.starxmind.bass.datastructure.tree.test;

import com.starxmind.bass.datastructure.tree.ExpiringMap;
import org.junit.Test;

public class ExpiringMapTest {
    @Test
    public void test() {
        ExpiringMap<String, Integer> expiringMap = new ExpiringMap<>();
        expiringMap.put("key1", 100, 5000); // 在5秒后过期
        expiringMap.put("key2", 200, 10000); // 在10秒后过期

        System.out.println(expiringMap.get("key1")); // 输出：100
        System.out.println(expiringMap.get("key2")); // 输出：200

        // 等待一段时间
        try {
            Thread.sleep(6000); // 等待6秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(expiringMap.get("key1")); // 输出：null，因为已经过期
        System.out.println(expiringMap.get("key2"));

        expiringMap.shutdown(); // 关闭过期检查的线程池
    }
}
