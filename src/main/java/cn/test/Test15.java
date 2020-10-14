package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author:Summer
 * @Date:2020/9/11 16:22
 * @Description:
 *      打断线程park方法，判断打断标志是否为假才会暂停线程,这个方法使得线程暂停在这里后面的代码不会继续执行，使用打断方法interrruot将打断标记设置为真可以继续执行part方法之后的代码
 */
@Slf4j(topic="c.test15")
public class Test15 {
    public static void main(String[] args) throws InterruptedException {
      Thread thread=  new Thread(()->{
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark");
            log.debug("打断状态 {}",Thread.currentThread().isInterrupted());//可以使用Thread.interrupted得到线程是否被打断过的信息，如果被打断过重置打断标记
        },"t1");
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
