package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/22 18:19
 * @Description:  尝试线程池超时或者没有超时，这种情况下，会出现一种情况：生产者太多了，而执行线程的cpu和消息队列不够存放
 */
@Slf4j(topic="c.test")
public class Test51 {
    public static void main(String[] args) {
         ThreadPool  pool=new ThreadPool(1,1000,TimeUnit.MILLISECONDS,1,(queue, task) ->{queue.put(task);});
        for (int i = 0; i < 3; i++) {
            int j=i;
            pool.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}",j);
            });
        }
    }
}
//定义线程池
@Slf4j(topic="c.test")
class ThreadPool
{
    //任务队列
     private BlockingQueue<Runnable>taskQueue;
     //线程集合
     private HashSet<Woker>wokers=new HashSet();
     //核心线程数
    private int coreSize;

    //设置超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    //执行任务
    public void execute(Runnable task)
    {
        //当任务树没有超过coreSize时，直接交给woker对象执行
        synchronized (wokers) {
            if (wokers.size() < coreSize) {
                log.debug("新增worker{},{}",wokers,task);
                Woker woker = new Woker(task);
                wokers.add(woker);
                woker.start();
            }
            //如果任务超过coreSize时，加入任务队列暂存
            else {
                taskQueue.tryPut(rejectPolicy,task);
                //拒绝策略
                /*
                                   1死等 put()
                                   2超时等待 offer()
                                   3让调用者抛出异常   throw new RuntimeExction("任务执行失败 {}",+task},前面的任务抛出了异常，后面的线程根本不会执行（没有机会放弃任务），但是抛出异常之后，前面没有执行完成的代码会继续执行
                                   4让调用者放弃任务执行  什么操作都不做 log.debug("放弃执行任务 {}",task}，每一个线程都会执行（而任务会自动放弃）
                                   5调用者自己执行任务


                 */
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapcity,RejectPolicy<Runnable >rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue=new BlockingQueue<>(queueCapcity);
        this.rejectPolicy=rejectPolicy;
    }

    class  Woker extends Thread
     {
        private Runnable task;

         public Woker(Runnable task) {
             this.task = task;
         }

         @Override
         public void run() {
             //执行任务
             /*
                当task不空，执行任务
                当task执行完毕，再接着从任务队列获取任务并执行
              */
             while(task!=null||(task=taskQueue.poll(timeout,timeUnit))!=null)
             {
                 try
                 {
                     log.debug("正在执行...{}",task);
                     task.run();
                 }catch (Exception e)
                 {
                     e.printStackTrace();
                 }
                 finally {
                     task=null;
                 }
             }
            synchronized (wokers)
            {
                log.debug("worker 被移除{}",this);
                wokers.remove(this);
            }

         }
     }
}
@Slf4j(topic="c.test")
class BlockingQueue<T>
        {
            //1任务队列
            private Deque<T>queue=new ArrayDeque<>();
            //2锁
            private ReentrantLock reentrantLock=new ReentrantLock();
            //3生产者条件变量
            private Condition fullWaitSet=reentrantLock.newCondition();
            //4消费者条件变量
            private Condition  emptyWaitSet=reentrantLock.newCondition();
            //容量
            private int capcity;
            //设置有时限的阻塞获取
            public  T poll(long timeout, TimeUnit unit)
            {

                reentrantLock.lock();
                try {
                    //将超时时间统一换为纳秒
                    long nanos=unit.toNanos(timeout);
                    while (queue.isEmpty()) {
                        try {
                            //返回的是剩余时间
                            nanos = emptyWaitSet.awaitNanos(nanos);
                            if(nanos<=0) {

                            return null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    T t = queue.removeFirst();
                    fullWaitSet.signal();
                    return t;
                }
                finally {
                    reentrantLock.unlock();
                }
            }
            //阻塞获取
            public T take()
            {
                reentrantLock.lock();
               try {
                   while (queue.isEmpty()) {
                       try {
                           emptyWaitSet.await();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
                   T t = queue.removeFirst();
                   fullWaitSet.signal();
                   return t;
               }
               finally {
                   reentrantLock.unlock();
               }

            }
            //阻塞添加
            public void put(T element)
            {
                reentrantLock.lock();
                try {
                    while (queue.size()==capcity) {
                        try {
                            log.debug("等待加入任务队列。。。{}",element);
                            fullWaitSet.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("加入任务队列{}",element);
                   queue.addLast(element);
                    emptyWaitSet.signal();
                }
                finally {
                    reentrantLock.unlock();
                }
            }
            //获取大小
            private int size()
            {
                reentrantLock.lock();
                try
                {
                     return queue.size();

                }
                finally {
                    reentrantLock.unlock();
                }
            }

            public BlockingQueue(int capcity) {
                this.capcity = capcity;
            }

            //带超时时间的阻塞添加

            public  boolean offer(T task ,long timeout, TimeUnit timeUnit)
            {
                reentrantLock.lock();
                try {
                    long nanos=timeUnit.toNanos(timeout);
                    while (queue.size()==capcity) {
                        try {
                            log.debug("等待加入任务队列。。。{}",task);
                            if(nanos<=0)
                            {
                                return false;
                            }
                            nanos=fullWaitSet.awaitNanos(nanos);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    log.debug("加入任务队列{}",task);
                    queue.addLast(task);
                    emptyWaitSet.signal();
                    return true;
                }
                finally {
                    reentrantLock.unlock();
                }
            }

            public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
                reentrantLock.lock();
                try {
                    //判断队列是否已满
                    if(queue.size()==capcity)
                    {
                        rejectPolicy.reject(this,task);
                    }
                    else
                    {
                        log.debug("加入任务队列{}",task);
                        queue.addLast(task);
                        emptyWaitSet.signal();
                    }


                }
                finally {
                    reentrantLock.unlock();
                }
            }
        }
//抽象接口
interface RejectPolicy<T>
{
    void reject(BlockingQueue<T> queue,T task);

}