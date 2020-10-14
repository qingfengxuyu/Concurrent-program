package cn.itcast.n4.exercise;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/15 11:54
 * @Description:
 *                   批量重偏向
 *             如果对象虽然被多个线程访问，但是没有竞争，这时偏向线程T1的对象仍有可能重新偏向它，重偏向有重置对象的 线程id
 *                  批量撤销
 *              当撤销偏向超过40次以后，java虚拟机会觉得自己的确偏向错了
 *
 */
@Slf4j(topic="测试轻量级锁")
public class TestCorrectPos {
    public static void main(String[] args) {
        Object o=new Object();
        new Thread(()->
        {
            log.debug("哈哈");
            synchronized (o)
            {
                log.debug("进程");
            }
            log.debug("吃的");
            synchronized (TestCorrectPos.class)
            {
                TestCorrectPos.class.notify();
            }
        }).start();

        new Thread(()->{
           synchronized (TestCorrectPos.class)
           {
               try {
                   TestCorrectPos.class.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               log.debug("呵呵");
               synchronized (o)
               {
                   log.debug("新线程1");
               }
               log.debug("喝的");

           }
        }).start();

    }
}
