package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/17 16:29
 * @Description:
 *                                                  ReentrantLock可重入锁
 *             相对于synchronied它具有的特点： 可以中断，可以设置超时时间，可以设置为公平锁，支持多个条件变量
 *             共同点：都支持可重入（指的是同一个线程如果首次获得这一把锁，那么因为它是这把锁的所有者，因此有权利再一次获取这把锁，如果是不可重入锁，那么第二次获得锁的实惠，自己也会被锁挡住）
 *
 *
 */
@Slf4j(topic="c.test")
public class TestReentranLock {
    private  static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            log.debug("enter main");
            m1();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
    public static void m1()
    {
        lock.lock();
        try {
           log.debug("enter m1");
           m2();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
    public static void m2()
    {
        lock.lock();
        try {
          log.debug("enter m2");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }


}
