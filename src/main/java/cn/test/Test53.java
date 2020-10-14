package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * @Author:Summer
 * @Date:2020/9/23 14:52
 * @Description:           测试newCacheThreadPool
 */
@Slf4j(topic="c.test52")
public class Test53 {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer>integers=new SynchronousQueue<>();
        new Thread(()->{
            try{
                log.debug("putting{}",1);
                integers.put(1);
                log.debug("{} putted...",1);
                log.debug("puting...{}",2);
                integers.put(2);
                log.debug("{} putted...",2);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1);
        new Thread(()->{

            try {
                log.debug("takeing {}",1);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1);
        new Thread(()->{

            try {
                log.debug("takeing {}",2);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
