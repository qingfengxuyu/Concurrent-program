package cn.itcast.test1;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/19 11:21
 * @Description:
 *                              交替执行，三个线程输出内容进行同步
 *               整数标记  不同的数，执行不一样的线程
 *
 */
@Slf4j(topic="c.test")
public class NewTest2 {
    public static void main(String[] args) {
        WaitNotify waitNotify=new WaitNotify(1,5);


        new Thread(()->{
            waitNotify.print("a",1,2);
        }).start();
        new Thread(()->{
            waitNotify.print("b",2,3);
        }).start();
        new Thread(()->{
            waitNotify.print("c",3,1);
        }).start();
    }
}
class WaitNotify
{
    private int flag;
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }
    public  void  print(String str,int waitFlag ,int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {


            synchronized (this) {
                while (flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("\t"+str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}

