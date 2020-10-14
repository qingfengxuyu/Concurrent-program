package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/17 15:22
 * @Description:
 *       死锁：在获取对方锁的过程中进入不断阻塞的状态（解决方法：顺序加锁，这种解决方法 容易造成饥饿问题）
 *       活锁，几个线程之间都没有进入阻塞状态，而是不断改变对方线程的结束状态，导致几个线程间永远结束不了
 *       饥饿：一个线程的优先级太低，始终得不到cpu的调度执行，也不能够结束
 *
 */
@Slf4j(topic="c.main")
public  class MainTest1
        {
            public static void main(String[] args) {
                Chopstick t1=new  Chopstick("1");
                Chopstick t2=new  Chopstick("2");
                Chopstick t3=new  Chopstick("3");
                Chopstick t4=new  Chopstick("4");
                Chopstick t5=new  Chopstick("5");
                new Test28("苏格拉底",t1,t2).start();
                new Test28("柏拉图",t2,t3).start();
                new Test28("亚里士多德",t3,t4).start();
                new Test28("赫拉克利特",t4,t5).start();
                new Test28("阿基米德",t1,t5).start();
            }
        }
@Slf4j(topic="c.test28")
 class Test28 extends  Thread{
    Chopstick left;
    Chopstick right;
    public  Test28(String name,Chopstick left,Chopstick right)
    {
        super(name);
        this.right=right;
        this.left=left;
    }
    @Override
    public void run() {
        while(true)
        {
            synchronized (left)
            {
                synchronized (right)
                {
                    eat();
                }
        }
        }
    }
    private void eat()
    {
        log.debug("eating");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Chopstick
{
    private  String name;

    public Chopstick(String name) {
        this.name = name;
    }
}