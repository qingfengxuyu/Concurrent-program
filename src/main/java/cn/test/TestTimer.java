package cn.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Summer
 * @Date:2020/9/24 11:35
 * @Description:
 *                                                                  任务调度线程池
 *                         在任务调度线程池功能加入之前，可以使用java.util.Timer来实现定时功能呢，Timer的优点在于简单易用，但是由于所有的任务都是由同一个线程来调度，因此所有的
 *                         任务都是串行执行，前一个任务的延迟或异常都会影响到之后的任务
 *                         callable会处理异常封装在get方法中，由future对象获取
 *
 */
@Slf4j(topic="c.testTimer")
public class TestTimer {
    public static void main(String[] args) {
        //method1();
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(2);//这个线程池可以延时，重复执行
        //method2(pool);
        pool.scheduleAtFixedRate(()->{log.debug("runing");},1,3,TimeUnit.SECONDS);
       // pool.scheduleWithFixedDelay()是完成上一个任务之后延迟多久执行一次
    }

    private static void method2(ScheduledExecutorService pool) {
        pool.schedule(()->{
            log.debug("atsk1");
        },1, TimeUnit.SECONDS);
        ScheduledExecutorService pool1= Executors.newScheduledThreadPool(2);
        pool.schedule(()->{
            log.debug("atsk2");
        },1,TimeUnit.SECONDS);
    }

    private static void method1() {
        Timer timer=new Timer();
        TimerTask task1=new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("task 1");
                Thread.sleep(2000);
            }
        };
        TimerTask task2=new TimerTask() {

            @Override
            public void run() {
                log.debug("task 2");

            }
        };
        timer.schedule(task1,1000);
        timer.schedule(task2,1000);
    }
}
