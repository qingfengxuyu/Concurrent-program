package cn.itcast.n4.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @Author:Summer
 * @Date:2020/9/12 14:18
 * @Description:
 */
@Slf4j(topic="c.ExerciseSell")
public class ExerciseSell {
    public static void main(String[] args) throws InterruptedException {
        TicketWindow ticketWindow=new TicketWindow(1000);
        List<Integer>list=new Vector<>();
        List<Thread>threadList=new ArrayList<>();

        for (int i = 0; i < 2000 ;i++) {
            Thread thread=new Thread(()->{
                int a=ticketWindow.sell(randowAmount());
                try {
                    Thread.sleep(20);//模拟写数据停顿现象
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(a);
            });
            threadList.add(thread);
            thread.start();

        }
        for (Thread thread1 : threadList) {
            thread1.join();
        }
        //统计卖出的票数和剩余的票数
        log.debug("余票 {}",ticketWindow.getCount());
        log.debug("卖出的票数 {}",list.stream().mapToInt(i->i).sum());


    }
    static Random r=new Random();
    public  static  int randowAmount(){
        return  r.nextInt(5)+1;
    }


}
class TicketWindow
{
    private  int count;

    public TicketWindow(int count) {
        this.count = count;
    }
    public int getCount()
    {
        return count;
    }
    public  int sell(int acount)
    {
        if(this.count>=acount)
        {
            this.count=count-acount;
            return  acount;
        }
        else
        {
            return 0;
        }
    }
}
//账户
 class Account
{
    private  int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public void transfer(Account target,int amount)
    {
        if(this.money>=amount)
        {
            this.setMoney(this.getMoney()-amount);
            target.setMoney(target.getMoney()+amount);//银行转账问题，共享变量是两个对象的menoy，但是在方法上加锁，只是锁住了本身对象的共享变量，
            //这种情况下：对象a的共享变量锁住了，对象b操作对象a的数据都是读写一致的，但是对象a读取对象b的共享变量的时候是状态不一致的读写
            //比如 账户a读取一个账户b 余额，更改自己账户的信息，但是账户b的余额信息不受保护，在改的时候，出现了不一致
            //暂时的解决方法是，锁住无关具体对象的锁，可以使用类对象的锁 Test.class（这是一个单例锁，所有线程共享一个数据）
        }
    }
}

