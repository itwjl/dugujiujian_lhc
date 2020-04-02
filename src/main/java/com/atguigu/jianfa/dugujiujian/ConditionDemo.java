package com.atguigu.jianfa.dugujiujian;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    int number = 1;

    /**
     *
     * @param num 循环次数
     * @param flag 当前线程标志位
     * @param lock
     * @param c1
     * @param c2
     */
    public void print(int num,int flag, ReentrantLock lock, Condition c1, Condition c2) {
        lock.lock();
        try {
            // 1.判断
            while (number != flag) {
                c1.await();
            }
            // 2.干活
            for (int i = 0; i < num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3.通知
            if (number != 3) {
                number ++;
            } else {
                number = 1;
            }
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {


    public static void main(String[] args) {
        ShareData shareData = new ShareData();


        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareData.print(3, 1, lock, c1, c2);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareData.print(4,2, lock, c2, c3);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                shareData.print(5, 3, lock, c3, c1);
            }
        }, "C").start();


    }
}
