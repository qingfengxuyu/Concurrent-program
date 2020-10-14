package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 15:19
 * @Description:
 *              打断正在运行的线程,如果是循环线程，并不会终止这个线程
 *              在被打断的线程中，获取到打断标记，自己决定是否继续运行，还是结束这个线程的执行
 */
@Slf4j(topic="c.test13")
public class Test13 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->
        {
            while (true){
              boolean b= Thread.currentThread().isInterrupted();
              if(b)
              {
                  log.debug("被打断了，终止循环");
                  break;
              }
            }
        },"t1");
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
