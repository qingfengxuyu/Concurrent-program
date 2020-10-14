package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/20 10:44
 * @Description:
 *                                             happens-before 规定了共享变量的写操作对其他线程读操作的可见性，它是可见性与有序性的一套规则总结，
 *             1线程解锁m之前对变量的写，对于接下来对m加锁的其他线程对该变量的读可见
 *             2线程对vvolatile变量的写，对于接下来其他线程对该变量的读可见性
 *             3线程start之前对变量的写，对线程开始后对该变量的读可见
 *             4线程结束前对变量的写，对其他线程得知它结束后的读可见，（比如调用t1.isAlive（）或者t1.join（）等待它结束）
 *             5线程打断另一个线程，对于其他线程知道被打断之后，对变量读可见
 *             6对变量默认值（0，false，null），对于其他线程对该变量的读可见
 *             7.具有传递性，
 *
 *
 */
@Slf4j(topic="c.test38")
public class Test38 {
    public static void main(String[] args) {
        log.debug("1111");
    }
}
