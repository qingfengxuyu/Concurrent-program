package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author:Summer
 * @Date:2020/9/23 15:20
 * @Description:
 */
@Slf4j(topic = "c.testSubmit")
public class TestSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        //method1(executorService);

        method2(executorService);
    }

    private static void method2(ExecutorService executorService) throws InterruptedException, ExecutionException {
        List<Future<String>> future=executorService.invokeAll(Arrays.asList(()->{log.debug("begin");
        Thread.sleep(1000);
        return "1";
        },
                ()->{log.debug("begin");
                    Thread.sleep(100);
                    return "2";
                },
                ()->{log.debug("begin");
                    Thread.sleep(500);
                    return "3";
                }


        ));
        future.forEach(f-> {
                    try {
                        log.debug("{}",f.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private static void method1(ExecutorService executorService) throws InterruptedException, ExecutionException {
        Future<String> future=executorService.submit(() -> {
            log.debug("......");
            Thread.sleep(1000);
            return  "ok";
        });
        log.debug("{}",future.get());//get方法会阻塞
    }
}
