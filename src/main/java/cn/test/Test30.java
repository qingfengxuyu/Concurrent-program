package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/17 17:02
 * @Description:
 */
@Slf4j(topic = "fd")
public class Test30 {
    private static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            try{
                log.debug("......");
                System.out.println("dsfd");
                lock.lockInterruptibly();//使用这种锁的这个方法可以将这个进程打断，防止无限制的等待，抛出异常，可以避免死锁的发生

            }catch (Exception e)
            {
                e.printStackTrace();

            }
            try
            {
                System.out.println("...");
                log.debug("获取到锁");
            }
            finally {
                lock.unlock();
            }
        });
        lock.lock();
        t1.start();
    }
}
