package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/12 11:28
 * @Description:
 *                                                          变量的线程安全分析
 *        成员变量和静态变量：
 *                1 如果他们没有共享，线程安全
 *                2 线程共享只有读，线程安全
 *                3 如果线程共享有读写操作 ，线程不安全
 *         局部变量：
 *                1 局部变量是线程安全的
 *                2.局部变量引用的对象分情况
 *                   对象没有逃离方法的作用范围访问，它是线程安全的
 *                   对象逃离方法的作用范围，则需要考虑线程安全
 *
 *
 */
@Slf4j(topic="c.test20")
public class Test20 {
    public static void main(String[] args) {
     log.debug("dsds");
    }
}
