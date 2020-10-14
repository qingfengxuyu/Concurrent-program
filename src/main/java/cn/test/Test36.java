package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/19 15:24
 * @Description:
 *                     同步模式之犹豫模式：
 *                           用在：一个线程发现另一个线程或者本线程已经做了某一件相同的事，那么本线程就无需再做了，直接结束返回
 *                          判断标记使用同步锁，防止多线程同时读取到同一个值
 */
@Slf4j(topic="c.test")
public class Test36 {
    public static void main(String[] args) {
        log.debug("t");
    }
}
