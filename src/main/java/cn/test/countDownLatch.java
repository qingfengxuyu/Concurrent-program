package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Summer
 * @Date:2020/9/25 17:41
 * @Description:
 *   倒计时锁
 *          线程池固定，不会释放因此不会考虑join而是使用这个高级的计数API
 */
@Slf4j(topic="c.test")
public class countDownLatch {
    public static void main(String[] args) {


        ExecutorService pool = Executors.newFixedThreadPool(2);
        CyclicBarrier barrier = new CyclicBarrier(2,()->{log.debug("task1,task2 finish");});
        for (int i = 0; i < 3; i++) {
            pool.submit(()->{
                log.debug("task1 begin...");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                finally {
                    log.debug("task1 end...");
                }
            });
            pool.submit(()->{
                log.debug("task2 begin...");
                try {
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                finally {
                    log.debug("task2 end...");
                }
            });

        }
        pool.shutdown();
    }
}
