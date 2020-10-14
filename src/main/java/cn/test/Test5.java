package cn.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Test5 {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
    }
}
