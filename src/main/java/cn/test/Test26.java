package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/17 14:45
 * @Description:
 */
@Slf4j(topic="c.test26")
public class Test26 {
    public static void main(String[] args) {


        BigRoom bigRoom = new BigRoom();

        new Thread(() -> {
            try {
                bigRoom.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                bigRoom.study();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}


@Slf4j(topic="c.test26")
  class  BigRoom
{
    private  final  Object stu=new Object();
    private  final  Object sl=new Object();
     public void sleep() throws InterruptedException {
         synchronized (sl)
         {
             log.debug("sleeping 2小时");
             Thread.sleep(20000);
         }
     }
     public void study() throws InterruptedException {
         synchronized (stu)
         {
             log.debug("study 1小时");
             Thread.sleep(10000);
         }
     }

}

