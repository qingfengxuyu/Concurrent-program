package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/19 09:33
 * @Description:
 *                尝试获得锁，有返回值，不会进入等待时间
 */
@Slf4j(topic="newTest")
public class NewTest {
    private static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        Thread t1=new Thread(()->
        {
            log.debug("尝试获得锁");
           if(! lock.tryLock()) {
               log.debug("获取不到锁");
               return;
           }
           try
           {
               log.debug("获得锁");
           }
           finally {
               lock.unlock();
           }
        });
        t1.start();
    }

}
