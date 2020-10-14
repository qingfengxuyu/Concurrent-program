package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 15:38
 * @Description:
 *         两阶段终止模式
 *                第一阶段是while tru程序运行期间出现打断，就直接料理后事，然后结束循环
 *                第二阶段是：程序运行期间没有出现打断，而是在睡眠期间出现打断，这种情况下需要
 *                           重新设置打断标记为真，然后等待程序运行后料理后事，然后结束程序
 */
@Slf4j(topic="c.test14")
public class Test14 {
    public static void main(String[] args) throws  Exception {
        TwoPhaseTermination twoPhaseTermination=new TwoPhaseTermination();
        twoPhaseTermination.start();
        Thread.sleep(6000);
        twoPhaseTermination.stop();

    }
   static class  TwoPhaseTermination//使用静态类，我哭了，不懂
    {
        //启动监控线程
        private  Thread moniter;
        public  void start()
        {
            moniter=new Thread(()->{
                while (true)
                {
                   if(Thread.currentThread().isInterrupted())
                   {
                       log.debug("料理后事");
                       break;
                   }
                    try {
                        Thread.sleep(2000);
                        log.debug("执行监控记录");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();//抛出异常之后，会继续执行抛出异常之后的代码，这时候使用打断方法会重新设置打断标志，下一次线程再次启动的时候
                                                           //可以继续执行,被打断之后，程序可以继续运行
                    }
                }
            }
            );
            moniter.start();
        }
        //停止监控线程
        public  void stop()
        {
            moniter.interrupt();
        }
    }
}
