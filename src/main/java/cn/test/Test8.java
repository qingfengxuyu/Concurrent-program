package cn.test;

/**
 * @Author:Summer
 * @Date:2020/9/11 11:47
 * @Description:
 *            yield方法让出了当前线程的cpu使用，从runing状态进入runnable就绪状态，
 *            具体实现看操作系统的任务调度器（如果cpu空闲，也有可能会继续执行当前线程）
 *
 *         线程优先级：仅仅对调度器起到一个提示的作用，调度器可以忽略他，没有设置线程优先级的时候，默认都是5
 *                    如果cpu比较忙，那么优先级线程会获得更多的时间片，但cpu空闲的时候，基本没有什么用
 *
 */
public class Test8 {
    public static void main(String[] args) {
        Runnable task1=()->{
            int count=0;
            for(;;)
            {
                System.out.println("--->1   "+count++);
            }
        };
        Runnable task2=()->
        {
          int count=0;
          for(;;)//工作台应该是抢占式
          {
              //Thread.yield();//线程让出cpu
              System.out.println("       --->2   "+count++);
          }
        };
        Thread t1=new Thread(task1,"t1");
        Thread t2=new Thread(task2,"t2");
        t1.setPriority(1);
        t2.setPriority(8);
        t1.start();
        t2.start();
    }
}
