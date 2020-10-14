package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 17:59
 * @Description:
 * 统筹规划:设计两个人烧水泡茶过程
 */
@Slf4j(topic="c.test17")
public class Test17 {
    public static void main(String[] args) {
        Thread thread=new Thread(()->
        {
            log.debug("洗水壶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("烧开水");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread thread1=new Thread(()->
        {
            log.debug("洗茶壶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("洗茶杯");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("拿茶叶");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
        });
        thread.start();
        thread1.start();
    }
}
