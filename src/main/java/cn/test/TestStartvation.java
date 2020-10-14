package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author:Summer
 * @Date:2020/9/24 10:47
 * @Description:
 *    这个例子是是线程的数量不足导致的饥饿问题，不是死锁问题
 *           解决方法：不同的线程池处理不同的任务
 *                          创建多少的线程比较合适
 *             过小会导致程序不能充分的利用系统资源，容易导致饥饿
 *             过大会导致更多的线程上下文切换，占用更多的内存
 *
 *          CPU密集型运算
 *                cpu核数+1 实现最优的cpu利用率，+1是保证当线程由于页缺失故障（操作系统或者其他的原因导致暂停时，额外的这个线程就能顶上去，保证cpu时钟周期不能浪费）
 *          I/O密集型运算
 *              cpu不总是处于繁忙状态，例如，当你执行业务计算的时候，这时候会使用cpu资源，但当你执行IO操作的时候，远程调用RPC的时候，包括进行数据库操作的时候，这时候cpu就闲置下来了
 *              可以利用多线程提高利用效率
 *           线程数=核数*期望cpu利用率*总时间（cpu计算时间+等待时间）/cpu计算时间
 *              举例（一个cpu期望每时每刻都在使用，计算时间是50%，等待时间间是50%（做io操作，这时候可以创建多一个线程利用cpu））4*100%*(100%/50%)
 */
@Slf4j(topic="c.testDeadlock")
public class TestStartvation {
    static final List<String>MENU= Arrays.asList("地三鲜","宫保鸡丁","辣子鸡丁","烤鸡腿");
    static Random RANDOM=new Random();
    static String coooking()
    {
        return MENU.get(RANDOM.nextInt(MENU.size()));

    }

    public static void main(String[] args) {
        ExecutorService waitpool= Executors.newFixedThreadPool(1);
        ExecutorService cookingpool= Executors.newFixedThreadPool(1);


        waitpool.execute(()->{
            log.debug("处理点餐问题..");//两个线程
            Future<String>f=cookingpool.submit(()->{log.debug("做菜");return coooking();});
            try {
                log.debug("上菜{}", f.get());

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            });
        waitpool.execute(()->{
            log.debug("处理点餐问题..");
            Future<String>f=cookingpool.submit(()->{log.debug("做菜");return coooking();});
            try {
                log.debug("上菜{}", f.get());

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });


    }
}
