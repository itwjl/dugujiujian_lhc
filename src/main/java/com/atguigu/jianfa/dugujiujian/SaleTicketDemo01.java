package com.atguigu.jianfa.dugujiujian;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 300;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() + "\t卖出第" + (number--) + "\t还剩下：" + number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        new Thread(()->{for (int i = 0; i < 301; i++)  ticket.sale();}, "A").start();
        new Thread(()->{for (int i = 0; i < 301; i++)  ticket.sale();}, "B").start();
        new Thread(()->{for (int i = 0; i < 301; i++)  ticket.sale();}, "C").start();
        /*new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 301; i++) {
                    ticket.sale();

                }
            }
        }, "AA").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 301; i++) {
                    ticket.sale();
                }
            }
        }, "BB").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 301; i++) {
                    ticket.sale();
                }
            }
        }, "CC").start();*/
    }
}
