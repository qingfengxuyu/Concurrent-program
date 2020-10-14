package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 11:38
 * @Description:
 */
@Slf4j(topic="c.test7")
public class Test7 {
    public static void main(String[] args) {
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                log.debug("enter sleep");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up");
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("interrupt");
        thread.interrupt();
    }
}
