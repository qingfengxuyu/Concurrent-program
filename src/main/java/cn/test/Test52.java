package cn.test;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Summer
 * @Date:2020/9/23 11:13
 * @Description:                                        jdk的线程池
 *       ExecutorService  基础接口
 *       ScheduleExecutorService  扩展接口
 *       ThreadPoolExecutor   扩展实现类
 *               使用int的高3位表示线程池状态，低29位表示线程数量，这些信息存放在一个原子变量ctl中，可以使用一次cas操作进行赋值
 *               状态名        高三位       接收新任务   处理阻塞队列任务   说明
 *               running       111（表示负数-3）          是           是                   不断的处理新任务，阻塞队列也会不断接受新任务
 *               shutdown      000 （0）         否           是                  温和的停止线程，处理完已经提交的任务（包括在阻塞队列中的任务）
 *               stop          001   （1）       否           否                 粗暴的停止线程， 会中断正在执行的任务，并抛弃阻塞队列
 *               tidying       010   （2）                                        任务全部实行完毕  活动线程为0即将进入终结
 *               terminated    011    （3）                                       终结状态
 *
 *               构造方法  public ThreadPoolExecutor(int corePoolSize  核心线程数目（最多保留的线程数）
 *                                                   int maxinumPoolSize  最大的线程数目
 *                                                   long keepLiveTime 生存时间-针对救急线程
 *                                                   TimeUnit timeUnit 时间单位-针对救急线程
 *                                                   BlockingQueue<Runnable> workQueue 阻塞队列
 *                                                   ThreadFactory threadFactory 线程工厂-可以为线程创建时起个好名字
 *                                                   RejectExecutionHandler  handler   拒绝策略
 *
 *
 *                         救急线程是阻塞队列不能存放任务后被创建
 *                         核心线程不会自动结束
 *
 *
 *       ScheduleThreadPoolExecutor 下层实现类
 *
 *
 *
 *
 *
 *
 *
 */
@Slf4j(topic="c.test")
public class Test52 {
    public static void main(String[] args) {
        ExecutorService pool= Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t=new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return  new Thread(r,"mypool_t"+t.getAndIncrement());
            }
        });
        pool.execute(()->{
            log.debug("1");
        });
        pool.execute(()->{
            log.debug("2");
        });
        pool.execute(()->{
            log.debug("3");
        });
    }
}
