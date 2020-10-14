package cn.test;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @Author:Summer
 * @Date:2020/9/11 14:38
 * @Description:
 *              演示三个进程之间的同步
 */
@Slf4j(topic="c.test11")
public class Test11 {
    static  int r1=0;
    static  int r2=0;

    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    public  static void test3()
    {

    }
    private  static  void test2() throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1=10;
        });
        Thread t2=new Thread(()->{
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2=20;
        });
        long  start=System.currentTimeMillis();
        t1.start();
        t2.start();
        log.debug("join begin");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end=System.currentTimeMillis();
        log.debug("r1:{}  r2:{}  cost:{}",r1,r2,end-start);
    }
}
