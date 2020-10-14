package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author:Summer
 * @Date:2020/9/24 15:33
 * @Description:              tomcat线程
 * limitLatch 用来限流，可以控制最大的连接个数
 * Accptor 只负责接收新的socket连接
 * Poller只负责监听socket chaanel是否有｛可读的I/O事件｝
 * 一旦可读，封装一个对象socketProcessor，提交给Executor线程池处理
 *     这个线程池扩展了ThreadPoolExecutor的行为；达到最大的线程数目之后，不会直接抛出异常，而是在一定的时间内尝试将任务再一次放入队列，不成功之后才会抛出异常
 *
 *                                                                   Fork/Join线程池的概念
 *                                       体现的是一种分治的概念
 *                            任务拆分  比如一些递归，归并排序，斐波那契队列，
 *                            这种线程池默认会创建与cpu核心数大小相同的线程池
 *
 *
 *
 */
@Slf4j(topic="c.test")
public class TestTomcatTheadPool {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool(4);
        forkJoinPool.invoke(new MyTask(5));

    }
}
//
@Slf4j(topic="c.test")
class MyTask extends RecursiveTask<Integer>
{
    private  int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {

        //终止拆分的条件
        if(n==1) {
            log.debug("join() {}", n);
            return 1;
        }
        return 1;
    }
}