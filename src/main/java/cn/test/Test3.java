package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
//使用具有返回结果的线程对象,可以抛出异常
@Slf4j(topic="c.Test")
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer>task=new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("runing");
                Thread.sleep(1000);
                return 100;
            }
        });
        Thread thread=new Thread(task);
        thread.start();
        log.debug("{}",task.get());//这个是主线程的代码块，但是要使用分线程的返回值结果，需要阻塞进行等待
    }
}
