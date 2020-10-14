package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 14:56
 * @Description:
 *     interrupt 打断sleep wait join之类的阻塞线程（休眠状态的线程）.
 *     线程被打断之后会有一个打断标记，打断之后会抛出异常信息并且重置打断标记为假
 *
 */
@Slf4j(topic="c.test12")
public class Test12 {
    public static void main(String[] args) throws InterruptedException {
         Thread thread=new Thread(()->
         {
             log.debug("sleep");
             try {
                 Thread.sleep(5000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });
         thread.start();
         Thread.sleep(1000);
         log.debug("interrupt");
         thread.interrupt();
        System.out.println("打断标记"+thread.isInterrupted());

    }

}
