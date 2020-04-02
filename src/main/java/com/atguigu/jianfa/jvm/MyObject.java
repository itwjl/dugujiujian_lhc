package com.atguigu.jianfa.jvm;

import com.atguigu.jianfa.dugujiujian.CallableDemo;

public class MyObject {
    public static void main(String[] args) {
        Object o = new Object();
        CallableDemo demo = new CallableDemo();
        System.out.println(demo.getClass().getClassLoader());
        System.out.println(o.getClass().getClassLoader());
    }
}
