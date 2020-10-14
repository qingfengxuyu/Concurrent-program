package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 14:17
 * @Description:
 *       join方法体现的是应用之间的同步（这里使用的多线程），异步一个线程不需要等待另一个线程的结果
 *
 */
@Slf4j(topic="c.test10")
public class Test10 {
    static  int r=0;
    public static void main(String[] args) {
        test1();
    }
    private  static  void test1()
    {
        log.debug("开始");
        Thread thread=new Thread(()->{
            log.debug("开始");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r=10;//主线程可以设置一个睡眠时间而获取更新之后的变量值，但是这个线程睡眠起来以后不一定会马上执行，所以不方便设置等待时间获取更新之后的变量值

        });
        thread.start();
        try {
            thread.join();//主线程运行到这里等待分线程执行结束后才取出更新之后的变量值
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("结果为:"+r);
        log.debug("结束");
    }
}
