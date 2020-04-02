package com.atguigu.jianfa.dugujiujian;

class Aircondition {
    private int number = 0;

    /**
     *
     */
    public synchronized void increment() {
        while (number == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();

    }

    /**
     *
     */
    public synchronized void decrement() {
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();

    }
}

public class ProdComsumerDemo01 {

    public static void main(String[] args) {
        Aircondition aircondition = new Aircondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                aircondition.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) aircondition.decrement();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) aircondition.increment();
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) aircondition.decrement();
        }, "D").start();
    }
}
