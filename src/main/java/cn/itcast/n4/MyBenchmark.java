package cn.itcast.n4;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/16 10:02
 * @Description:
 *    锁消除（自适应）
 *       加锁运算消耗一部分性能，但是有时候，它们的执行耗时基本差别不大
 *       java即使编译器，优化，局部变量对象的加锁操作，由于不会被别的线程共享，因此代码会优化为没有加锁
 *    可以使用参数将锁消除这个功能禁用；一旦禁止，性能相差很大
 *                                                 wait、notify
 *          Owner线程发现条件不足的时候，调用wait方法，进入WaitSet中变为Waiting状态
 *          Bolock和waitign的线程都处于阻塞状态，不占用cpu的时间片
 *          block的线程在owner线程释放锁的时候唤醒
 *          waiting线程会在Owner线程notify或者notifyAll的时候唤醒，但是唤醒之后并不意味着立刻获得锁，仍需要进入EntryList竞争
 *
 *          obj.wait()或者obj.wait(毫秒值)让进入object监视器的线程到waitSet中等待,必须是有锁状态才能调用
 *          obj.notify()挑选一个在object的waitset上一个线程进行唤醒
 *          obj.notifyAll() 全部唤醒
 *                      Sleep（n）和wait（n）的区别
 *             1）sleep是Thread方法，而wait是Object的方法
 *             2)不需要强制和Synchronied配合使用，但是wait需要和synchronied一起使用
 *             3）如果加了锁，sleep在睡眠的同时，不会释放对象锁的，但是wait等待的时候会释放锁对象
 *             共同点都会进入timed-waiting
 *
 *
 *
 *
 *
 *
 */
@Slf4j(topic="c.test")
public class MyBenchmark {
    static int x=0;

    public  void  a()
    {
        x++;
    }

}
