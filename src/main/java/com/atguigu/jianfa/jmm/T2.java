package com.atguigu.jianfa.jmm;

class MyNumber{

    volatile int number = 10;

    public void addTo1205(){
        this.number = 1205;
    }

}

/**
 *
 * @author 王敬磊
 * @email it_wjl@163.com
 * @date
 *
 * JMM = 可见性（通知机制）
 */
public class T2 {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.addTo1205();//将10修改为1205
            System.out.println(Thread.currentThread().getName()+"\t"+myNumber.number);
        }, "AAA").start();
        System.out.println(Thread.currentThread().getName()+"\t"+myNumber.number);
        while (myNumber.number == 10){
//            Thread.sleep(3000);
            // 需要有一种通知机制number已经修改为1205
        }
        System.out.println(Thread.currentThread().getName()+"\t"+myNumber.number);
    }
}
