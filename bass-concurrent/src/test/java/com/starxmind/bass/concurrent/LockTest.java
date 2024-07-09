package com.starxmind.bass.concurrent;

import org.junit.Test;

public class LockTest {
    @Test
    public void test() {
        LockTask lockTask = new LockTask();
        Runnable task = lockTask::execute;

        // 创建并启动多个线程
        Thread thread1 = new Thread(task, "T1");
        Thread thread2 = new Thread(task, "T2");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("lock status: " + lockTask.isLocked());
    }
}
