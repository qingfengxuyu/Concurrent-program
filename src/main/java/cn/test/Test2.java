package cn.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic="c.test2")
public class Test2 {
    public static void main(String[] args) {
        Runnable runnable= () -> log.debug("running");
        Thread thread=new Thread(runnable,"t2");
        thread.start();
        log.debug("run");
    }
}
