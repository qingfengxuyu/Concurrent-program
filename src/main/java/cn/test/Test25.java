package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * @Author:Summer
 * @Date:2020/9/17 11:44
 * @Description:
 *          LockSupport.part()//暂停当前线程
 *          LockSupport。unpark（暂停的线程对象）
 *          与wait notify方法差不多
 *          区别：如果unpark可在park之前使用，这个是以线程为单位，不需要加锁之类的操作
 *
 *          原理
 *                 每一个线程都有自己第一个Parker对象，由三部分组成 ——counter ，——cond 存储线程——mutex（互斥锁）
 *                 线程就像一个旅人，Parter 就像他随身携带的背包，条件变量就好比背包中的帐篷，——counter就是背包中储备的干粮（0为耗尽，1为充足）
 *                 调用park就是要看需不需要停下来歇息
 *                       如果备用干粮耗尽，就钻进帐篷休息
 *                       如果充足就不需要停留，继续前进
 *                  调用unpark 就好比干粮充足
 *                    如果这时候线程还在帐篷，就唤醒他继续前进
 *                    如果这时线程还在运行，那么他下次调用park时仅消耗储备干粮，不需要停留继续前进
 *                     因为背包空间有限，多次调用unpark仅补充一份备用干粮
 *
 *                    状态 ：
 *                     new   创建线程对象
 *                     runnable-waiting  使用对象的start（）方法
 *                        wait notify notifyall
 *                        注意 可以调用 t.join()进入等待，线程运行结束就可以进入runing，或者当前线程使用interrrupt也可以让目标线程进入ruing
 *                        park 和unpark
 *                     runnable--tiemd waiting
 *                          wait（）
 *                          join（）
 *                          sleep（）
 *                          parkNanos（）
 *                          parkUtils（）
 *
 *
 *
 *
 *
 *
 *
 *
 */
@Slf4j(topic="c.test25")
public class Test25 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            log.debug("start...");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");

        });
        t1.start();
        Thread.sleep(2);
        log.debug("unpark..");
        LockSupport.unpark(t1);


    }
}
