package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/19 14:33
 * @Description:               java内存模型
 *               它们定义了主存，工作内存的抽象概念，底层对应着cpu寄存器，缓存，硬件内存，cpu指令优化等
 *               JMM体现在以下两个方面
 *                  原子性 保证线程不会受到线程上下文切换的影响
 *                  可见性：保证指令不受cpu缓存的影响,
 *                           1.可以使用volatile(易变关键字，它可以用来修饰成员变量或静态成员变量，他可以避免线程从自己的工作缓存中查找变量的值，必须
 *                           到主存中获取他的值，线程操作的都是操作主存
 *                             ，这个关键字只能保证看到的是最新值，不能解决指令交错的问题
 *                           2、可以使用synchoried，可以保证变量的可见性，不过这种加锁，解锁的操作影响程序的性能，但是能保证
 *
 *                  有序性，保证指令不受cpu指令并行优化的影响指令的有序性
 *
 *                  可见性vs原子性
 *                    可见性不能保证原子性，仅用作一个写线程，多个读线程
 *
 *
 *
 *
 */
@Slf4j(topic="c.test")
public class Test35 {
  volatile   static  boolean run=true;
/*
         初始状态，t线程刚开始从主内存读取了静态变量到工作内存
         因为t线程要频繁的从主内存读取run的值，jit编译器就会将run的值缓存到自己工作的高速缓存中，并减少对主存的访问，提高效率
         1秒之后，main线程修改了run的值，并同步到主存，t永远是从自己的工作内存读值
 */
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            while (run)
            {

            }
        });
        t.start();
        Thread.sleep(1);
        log.debug("1");
        run=false;
    }

}
