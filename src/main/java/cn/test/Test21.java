package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/16 10:32
 * @Description:
 */
@Slf4j(topic="c.test")
public class Test21 {
    static final Object lock=new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            try {
                lock.wait();
                log.debug("dr");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
