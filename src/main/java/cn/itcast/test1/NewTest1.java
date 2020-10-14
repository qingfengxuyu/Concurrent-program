package cn.itcast.test1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/19 10:13
 * @Description:
 *                                         条件变量
 *                 synchronied 中也有条件变量，就是我们讲的那个waitSet休息室，当条件不满足的时候进入这里等待
 *                 ReentranLock的条件变量比synchronid强大之处在于它是支持多个条件变量的
 *
 *                 使用流程
 *                       await 前需要获得锁
 *                       await执行后会释放锁，进入conditionObject进行等待
 *                       await的线程被唤醒之后（或者打断，超时）取的重新竞争的lock锁
 *                       竞争lock锁成功，await后继续执行
 *
 */
@Slf4j(topic="d.test")
public class NewTest1 {
   static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        //创建条件变量
        Condition condition1=lock.newCondition();
        Condition condition2=lock.newCondition();
        lock.lock();
        condition1.await();
        condition1.signal();
    }
}
