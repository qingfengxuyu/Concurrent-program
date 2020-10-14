package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author:Summer
 * @Date:2020/9/19 12:28
 * @Description:
 */
@Slf4j(topic="c.test34")
public class Test34 {
        static Thread t1;
        static Thread t2;
        static Thread t3;
    public static void main(String[] args) {
                  ParkUnpark parkUnpark=new ParkUnpark(5);
                  t1=new Thread(()->
                  {
                      parkUnpark.print("a",t2);
                  });
        t2=new Thread(()->
        {
            parkUnpark.print("b",t3);
        });
        t3=new Thread(()->
        {
            parkUnpark.print("c",t1);
        });
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }

}
class ParkUnpark
{
    private int loopNumber;
     public void print(String str,Thread nest)
     {
         for (int i = 0; i < loopNumber; i++) {
             LockSupport.park();
             System.out.println(str);
             LockSupport.unpark(nest);
         }
     }

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }
}
