package cn.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Summer
 * @Date:2020/9/20 16:16
 * @Description:
 *
 */
public class Test40 {
    public static void main(String[] args) {
        AtomicInteger i=new AtomicInteger(0);
        System.out.println(i.incrementAndGet());
        System.out.println(i.getAndIncrement());
        System.out.println(i.addAndGet(5));
        System.out.println(i.get());
    }
}
