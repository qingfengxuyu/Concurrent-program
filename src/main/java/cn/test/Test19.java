package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/12 10:06
 * @Description:
 *               synchronized,俗称锁对象，它采用互斥的方式让同一时刻至多有一个线程持有对象锁，其他线程再想获取这一个对象锁就会发生阻塞
 *               保证持有安全区锁对象的线程可以安全执行代码，不用担心线程切换上下文,获取到对象锁的线程执行结束之后会唤醒阻塞的线程
 *               放在方法上的synchronied有两种方式
 *                    1 成员方法
 *                         class  Test{
 *                             public synchronied void test()
 *                             {
 *                                 count++
 *                             }
 *                         }
 *                         =========等价于=================
 *                         class  Test{
 *  *                             public void test()
 *                                  synchronied （this）
 *  *                             {
 *  *                                 count++
 *  *                             }
 *  *                         }
 *                        2静态方法
 *                               class  Test{
 *  *                             public synchronied static void test()
 *  *                             {
 *  *                                 count++
 *  *                             }
 *  *                         }
 *                         ===================等价于=================
 *                         class  Test{
 *  *                             public static void test()
 *                                synchronied (Test.class)//这是类对象
 *  *                             {
 *  *                                 count++
 *  *                             }
 *  *                         }
 *
 *                   下面的循环代码块
 *                                获取对象锁的线程即使时间片使用完了，发生了线程上下文的切换，也不会把锁释放，而是将其他线程阻塞后再次切换回有对象锁的线程执行
 *                       思考
 *                           1 对象锁放在循环外面，5000*4行指令先执行  原子性
 *                           2 对象锁1，对象锁2 达不到临界区保护的效果（不能起到阻塞效果）
 *                           3 只有一个线程加锁，另一个线程没有加锁（不能起到阻塞效果）
 *
 *                  注意
 *                     java中互斥和同步都采用synchronied关键字来完成，但是它们还是有区别
 *
 *
 */
@Slf4j(topic = "c.test19")
public class Test19 {
    static  int count=0;
    static final Object room=new Object();
    public static void main(String[] args) throws InterruptedException {
            Thread thread=new Thread(()->{
                for (int i = 0; i < 50000000; i++) {
                    synchronized (room) {
                        count++;
                    }
                }
            });
            Thread thread1=new Thread(()->{
                for (int i = 0; i < 50000000; i++) {
                    synchronized (room) {
                        count--;
                    }
                }
            });
            thread.start();
            thread1.start();
            thread.join();
            thread1.join();
            log.debug("{}",count);
        }
    }

