package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/19 10:41
 * @Description:
 *                              同步模式之顺序执行
 *                                synchoried wait 和 ReentranLock
 */
@Slf4j(topic="c.test")
public class Test32 {
    static  final Object lock=new Object();
    static   boolean b=false;
    //标记线程二是否执行过
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            synchronized (lock) {
                while (!b) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("1");
            }


        });
        Thread t2=new Thread(()->{
            synchronized (lock) {
                log.debug("2");
                b=true;
                lock.notify();
            }
        });
        t1.start();
        t2.start();
    }
}
