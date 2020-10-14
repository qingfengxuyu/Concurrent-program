package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;



/**
 * @Author:Summer
 * @Date:2020/9/22 09:23
 * @Description:
 */
@Slf4j(topic="c.test41")
public class Test41 {
    static AtomicReference<String>ref=new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        log.debug("main start....");
        String pre=ref.get();
        other();
        Thread.sleep(1);
        //尝试改为c
        log.debug("change A->C {}",ref.compareAndSet(pre,"C"));
        log.debug(ref.get());

    }

    private static void other() {
            new Thread(()->{log.debug("change A->B {}",ref.compareAndSet(ref.get(),"B"));}).start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{log.debug("change B->A {}",ref.compareAndSet(ref.get(),"A"));}).start();
    }
}
