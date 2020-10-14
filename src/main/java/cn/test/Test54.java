package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Summer
 * @Date:2020/9/23 15:08
 * @Description:
 */
@Slf4j(topic="c.test54")
public class Test54 {
    public static void main(String[] args) {
        test2();
    }
    public static void test2()
    {
        ExecutorService pool= Executors.newSingleThreadExecutor();
        pool.execute(()->{
            log.debug("1");
            int i=1/0;

        });
        pool.execute(()->{
            log.debug("2");

        });
        pool.execute(()->{
            log.debug("3");

        });
    }
}
