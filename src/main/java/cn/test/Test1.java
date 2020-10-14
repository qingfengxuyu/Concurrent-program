package cn.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test1")
//这一个主题标志着输出信息开头，下面的多线程可能由系统采用并发执行，或者是并行执行
public class Test1 {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
               log.debug("runing");
            }
        };
        thread.setName("t1");
        thread.start();
        log.debug("run");
    }

}
