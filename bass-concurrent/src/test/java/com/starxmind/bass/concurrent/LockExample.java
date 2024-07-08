package com.starxmind.bass.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        lock.lock(); // 尝试获取锁
        // 执行需要同步的代码
        System.out.println("执行任务");
    }

    public static void main(String[] args) {
        LockExample example = new LockExample();

        // 创建线程任务
        Runnable task = () -> example.performTask();

        // 创建并启动多个线程
        Thread thread1 = new Thread(task, "线程1");
        Thread thread2 = new Thread(task, "线程2");
        Thread thread3 = new Thread(task, "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}