package cn.itcast.test1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/19 09:33
 * @Description:
 *                尝试获得锁，有返回值，不会进入等待时间,有尝试获取锁的时间参数 trylock（n），别的线程也可以使用打断，结束进程
 */
@Slf4j(topic="c.newTest")
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
        lock.lock();
    }

}
