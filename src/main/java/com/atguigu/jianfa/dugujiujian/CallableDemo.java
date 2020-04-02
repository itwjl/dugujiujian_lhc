package com.atguigu.jianfa.dugujiujian;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("***** come in call method()");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread t1 = new Thread(new MyThread(), "A");
//        t1.start();
        FutureTask<Integer> task = new FutureTask<>(new MyThread2());
        new Thread(task, "A").start();
        Integer result = task.get();
        System.out.println(result);

    }
}
