package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * @Author:Summer
 * @Date:2020/9/25 11:58
 * @Description:                        读写锁
 * 实际上是使用统一syn的同步器，因此等待队列state分为两个标志信息
 */
@Slf4j(topic="c.testReadWriteLock")

public class TestReadWriteLock {
    public static void main(String[] args) {
        DataContainer dataContainer=new DataContainer();
        new Thread(()->{
            try {
                dataContainer.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                dataContainer.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

@Slf4j(topic="c.testReadWriteLock")
class DataContainer {
    private Object data;
    private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r=reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock w=reentrantReadWriteLock.writeLock();


    public Object read() throws InterruptedException {
        log.debug("获取读锁");
        r.lock();
        try {
            log.debug("读取");
            sleep(2000);
            return data;
        }
            finally {
            log.debug("释放读锁");
            r.unlock();
        }
    }
    public void  write()
    {
        log.debug("获取写锁");
        w.lock();
        try {
            log.debug("写入");
        } finally {
            log.debug("释放写锁");
            w.lock();
        }
    }
}