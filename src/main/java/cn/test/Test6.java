package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 11:19
 * @Description:
 *             sleep会使当前线程从running进入 timed waiting
 *             睡眠结束后的线程未必会立即执行
 *             建议使用TimeUint的sleep代替Thread的sleep来获取更好的刻度性
 */
@Slf4j(topic="c.test6")
public class Test6 {
    public static void main(String[] args) {
        Thread thread=new Thread("t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {//即使线程处于睡眠状态也有可能出现打断情况，这时候会抛出异常
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        log.debug(""+thread.getState());//注意使用字符拼接符号
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(""+thread.getState());
    }

}
