package cn.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic="c.Test4")
public class Test4 {
    public static void main(String[] args) {
        Thread t1=new Thread("t1"){
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        t1.run();//如果新线程不使用start方法，而是直接调用run方法，则run方法是属于主线程所拥有的，不会起到异步，提高效率的作用
    }
}
