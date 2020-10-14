package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Summer
 * @Date:2020/9/24 10:23
 * @Description:
 */
@Slf4j(topic="c.testshutdown")
public class ThreadPoolShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool= Executors.newFixedThreadPool(2);
        Future<Integer>result1=pool.submit(()->{
            log.debug("task1 runing ...");
            Thread.sleep(1000);
            log.debug("task1 finish");
            return 1;
        });
        Future<Integer>result2=pool.submit(()->{
            log.debug("task2 runing ...");
            Thread.sleep(1000);
            log.debug("task2 finish");
            return 1;
        });
        Future<Integer>result3=pool.submit(()->{
            log.debug("task3 runing ...");
            Thread.sleep(1000);
            log.debug("task3 finish");
            return 1;
        });
        log.debug("shutdown");
        pool.shutdown();
        //List<Runnable> runnables = pool.shutdownNow();打断正在运行的线程，返回队列中的任务
        log.debug("other");
        pool.awaitTermination(3, TimeUnit.SECONDS);//等待线程池结束之后进行执行后面的结果，可以使用future的get方法
        Future<Integer>result4=pool.submit(()->{
            log.debug("task4 runing ...");
            Thread.sleep(1000);
            log.debug("task4 finish");
            return 1;
        });
    }
}
