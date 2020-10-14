package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author:Summer
 * @Date:2020/9/24 16:31
 * @Description:
 *                                                                     AQS
 *                        全称是AbstractQueueSynchromieder 是阻塞式锁和相关同步器工具的框架
 *                        特点
 *                           state属性表示资源的状态（分独占模式和共享模式）
 *                           getState
 *                           setState
 *                           compareAndSetState  乐观锁机制设置state状态
 *                           有等待队列 类似EntrySet
 *                           支持多个条件变量 ，类似WaitSet
 *
 *                           tryAcquie（）尝试一次获取锁有返回值
 *                           tryRelease（）尝试释放锁
 *
 *
 */@Slf4j(topic="c.test")
public class AQS
{

    public static void main(String[] args) {
        MyLock myLock=new MyLock();
        new Thread(()->{
            myLock.lock();
            myLock.lock();
            try {
                log.debug("loking...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                log.debug("unloking...");
                myLock.unlock();
            }
        }).start();
        new Thread(()->{
            myLock.lock();
            try {
                log.debug("loking...");
            }
            finally {
                log.debug("unloking...");
                myLock.unlock();
            }


        }).start();
    }
}
//自定义不可重入锁
class MyLock implements Lock
{
    //独占锁，同步器
    class MySync extends AbstractQueuedSynchronizer
    {
        @Override
        protected boolean tryAcquire(int arg) {//这个参数为可重入锁做计数操作之类
            if(compareAndSetState(0,1))
            {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;

        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);//写屏障之前的不会指令重排序，对其他线程可见
            return true;
        }

        @Override//是否独占锁
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
        public Condition newConditon()
        {
            return  new ConditionObject();
        }
    }
    private MySync mySync=new MySync();//内部自己创建对象


    @Override//加锁
    public void lock() {
        mySync.acquire(1);
    }

    @Override//加锁可打断
    public void lockInterruptibly() throws InterruptedException {
              mySync.acquireInterruptibly(1);
    }

    @Override//尝试只一次加锁，就不进入等待队列
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override//带超时的尝试加锁
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override//解锁
    public void unlock() {
           mySync.release(1);
    }

    @Override//创建条件变量
    public Condition newCondition() {
        return mySync.newConditon();
    }
}

