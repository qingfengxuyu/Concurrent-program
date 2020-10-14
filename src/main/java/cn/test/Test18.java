package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/12 09:35
 * @Description:
 *  线程切换线程安全问题
 *         问题分析：对于静态变量而言，静态变量的自增和自减操作并不是一步完成的，
 *              获取静态变量的值
 *              准备常量1
 *              自减
 *              将修改之后的值放到常量
 *         临界区
 *              多线程访问共享资源的时候，读写操作发生了指令交错，就会出现问题
 *              多线程读取共享资源其实没有问题
 *              一段代码块之内如果存在对共享资源的读写操作，这段代码我临界区
 *         竞态条件
 *           多线程在临界区内执行，由于代码的执行序列不同导致结果无法预测，
 *          解决方案
 *            阻塞式解决方案：synchronized，lock
 *            非阻塞式解决方案：原子变量
 *
 */
@Slf4j(topic="c.test18")
public class Test18 {
    static  int count=0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                count++;
            }
        });
        Thread thread1=new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                count--;
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        log.debug("{}",count);
    }
}
