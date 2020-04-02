package com.atguigu.jianfa.dugujiujian;

@FunctionalInterface
interface Foo{
//    void sayHello();
    int add(int x,int y);
    default int mul(int x, int y){
        return x * y;
    }
    static int div(int x,int y){
        return x / y;
    }
}

/**
 * @author wjl
 *
 * 1 函数式编程
 *
 *
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {
//        Foo hello = () -> System.out.println("hello");

//        hello.sayHello();
        test();
    }

    private static void test() {
        Foo foo = (int x, int y) -> {
            System.out.println("come in");
            return x + y;
        };
        System.out.println(foo.add(3,4));

        System.out.println(foo.mul(3, 4));
        System.out.println(Foo.div(4, 5));
    }

}
