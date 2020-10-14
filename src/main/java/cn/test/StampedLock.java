package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @Author:Summer
 * @Date:2020/9/25 15:48
 * @Description:
 *                  不支持条件变量，不支持重入
 *                  Semaphore限制同时访问共享变量的线程上限
 *                  应用：限制访问高峰，可以实现简单的限制单级线程数量，不会限制连接的资源个数，
 *
 */
@Slf4j(topic="c.tests")
public class StampedLock {
    public static void main(String[] args) {
        Semaphore s=new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("runing..");
                try {
                    Thread.sleep(1000);
                    log.debug("end......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    s.release();
                }
            }).start();
        }
    }





}
