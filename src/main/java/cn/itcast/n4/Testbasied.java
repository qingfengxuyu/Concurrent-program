package cn.itcast.n4;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author:Summer
 * @Date:2020/9/16 09:28
 * @Description:
 *               撤销偏量
 */
@Slf4j(topic="c.test")
public class Testbasied {
    static Thread t1,t2,t3;
    private static void  test4()
    {
        Vector<Dog>list=new Vector<Dog>();
        int loopNumber=39;
        t1=new Thread(()->
        {
            for (int i = 0; i < loopNumber; i++) {
                Dog d=new Dog();
                list.add(d);
                synchronized (d)
                {
                    log.debug("er");
                }

            }
            LockSupport.park();
        });
        t1.start();
        t2=new Thread(()->
        {

            for (int i = 0; i < loopNumber; i++) {
                Dog d=new Dog();
                list.add(d);
                synchronized (d)
                {
                    log.debug("er");
                }

            }
        });
        t2.start();
    }
}
