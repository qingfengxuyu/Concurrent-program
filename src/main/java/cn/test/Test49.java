package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
 * @Author:Summer
 * @Date:2020/9/22 15:50
 * @Description:                        共享模型之不可变类
 *                               多线程模式下可以保证不可变类安全
 *
 */
@Slf4j(topic="c.test49")
public class Test49 {
    public static void main(String[] args) {
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
               synchronized (s)
               {
                   try{
                       log.debug("{}",s.parse("1995-04-21"));
                   }
                   catch (Exception e)
                   {
                       log.error("{}",e);
                   }
               }
            }).start();
        }
    }
}
