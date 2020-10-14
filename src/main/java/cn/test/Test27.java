package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/17 15:03
 * @Description:
 *           多把锁容易造成死锁
 */
@Slf4j(topic="c.test27")
public class Test27 {
    public static void main(String[] args) {
        test1();

    }
    private static void test1()  {
        Object A=new Object();
        Object B=new Object();
        Object C=new Object();
        Thread t1=new Thread(()->{
        synchronized (A)
        {
            log.debug("lock A");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B)
             {
                 log.debug("lock B");
                 log.debug("操作...");
             }
        }
        });
        Thread t2=new Thread(()->{
            synchronized (B)
            {
                log.debug("lock B");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A)
                {
                    log.debug("lock A");
                    log.debug("操作...");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
