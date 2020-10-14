package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 16:48
 * @Description:
 *                 主线程与守护线程
 *        默认情况下，java进程需要等待所有的线程执行结束之后才会结束，有一种特殊的线程叫作守护线程，只有其它非守护线程运行结束了，即使
 *        守护线程的代码没有执行结束也会强制结束进程
 *                   注意：垃圾回收器也是一种守护线程
 *                        Tomcat中Accept（接收请求）和Poller（分发请求）的守护线程，所以当Tomcat收shutdown命令之后，不会等待他们处理完当前的请求【】
 */
@Slf4j(topic="c.test16")
public class Test16 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            while (true)
            {
                if(Thread.currentThread().isInterrupted())
                {
                    break;
                }
            }
            log.debug("结束");
        });
        t1.setDaemon(true);//设置为守护线程
        t1.start();

        Thread.sleep(1000);
        log.debug("结束");
    }
}
