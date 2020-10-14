package cn.itcast.n4.exercise;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/16 11:23
 * @Description:
 *                                     同步模式之保护性暂停
 *                         有一个结果需要从一个线程传递到另一个线程，让他们关联统一个对象
 *                         如果有结果不断的从一个线程到拎一个线程可以使用消息队列（见生产者和消费者）
 *                         JDK中join的实现和Future的实现就是此模式
 *                         因为等待另一方的结果，因此归类到同步模式
 *
 */
@Slf4j(topic="c.test")
public class TestCorrectPostrue {
    static final Object room=new Object();
    static  boolean hasCiggrette=false;
    static  boolean hasTakeout=false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (room) {
                log.debug("有烟吗{}", hasCiggrette);

                if (!hasCiggrette) {
                    log.debug("没烟，先歇会");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没 {}", hasCiggrette);
                if (hasCiggrette) {
                    log.debug("可以开始干活");
                }
            }
        }).start();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized (room)
                {
                    log.debug("可以开始干活了");
                }
            }).start();

        }
        Thread.sleep(1);
        new Thread(()->{
            synchronized (room) {
                hasCiggrette = true;
                log.debug("烟到了哦！");
                room.notify();
            }
        }).start();
    }
}
