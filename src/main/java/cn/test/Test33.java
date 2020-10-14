package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Summer
 * @Date:2020/9/19 11:42
 * @Description:
 */
@Slf4j(topic="c.test")
public class Test33 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignl awaitSignl=new AwaitSignl(5);
        Condition a=awaitSignl.newCondition();
        Condition b=awaitSignl.newCondition();
        Condition c=awaitSignl.newCondition();
        Condition condition4=awaitSignl.newCondition();
        Condition condition5=awaitSignl.newCondition();
        new Thread(()->{
            awaitSignl.print("a",a,b);
        }).start();
        new Thread(()->{
            awaitSignl.print("b",b,c);
        }).start();
        new Thread(()->{
            awaitSignl.print("c",c,a);
        }).start();
        Thread.sleep(1000);
        awaitSignl.lock();
        try
        {
            a.signal();
        }
        finally {
            awaitSignl.unlock();
        }
    }
}
class AwaitSignl extends ReentrantLock
{
    private int loopNumber;

    public AwaitSignl(int loopNumber) {
        this.loopNumber = loopNumber;
    }
    //参数：打印的内容，要进入的休息室等待的线程，将下一个休息室的线程唤醒
    public void print(String str,Condition current,Condition next)
    {
        for (int i = 0; i < loopNumber; i++) {
            this.lock();//this
            try {
                current.await();
                System.out.println(str);
                next.signal();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                unlock();
            }

        }
    }
}

