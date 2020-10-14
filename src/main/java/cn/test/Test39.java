package cn.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Summer
 * @Date:2020/9/20 11:52
 * @Description:                                     无锁操作
 *         conpareAndSet 比较并设置值是原子性
 *
 *                过程：将取到的最新值与共享变量进行比较，如果不一致放弃修改，不断循环，一致就把修改之后的数进行写入
 *                CAS比较并交换，使用volatile修饰，这种情况下Ian保证可见性
 *
 *                区别：无锁效率高，即使重试失败，线程始终在高速运行，没有停歇，而synchroied会让线程在没有获得锁的前提下，发生上下文切换，进入阻塞
 *                     线程就好像高速，虽然不会进入阻塞，但是由于没有分到时间片，仍然会进入可运行状态
 *                     ，导致上下文切换
 *
 *                  CAS是基于乐观锁的思路，不怕别的线程来修改共享变量，就算改了也没有关系，重试多几次，无锁并发，无阻塞并发
 *                  synchronied基于悲观锁  ，
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Test39 {
    public static void main(String[] args) {
          Account account=new AcountUnsafe(10000);
        Account.demo(account);
        Account account1=new AccountCas(10000);
        Account.demo(account1);
}
}
class AccountCas implements Account
{
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return  balance.get();
    }

    @Override
    public void withDraw(Integer account) {
        while(true)
        {
            //获取余额的最新值
            int pre=balance.get();
            //要修改的余额
            int next=pre-account;
            //真正修改
            if(balance.compareAndSet(pre,next))
            {
                break;
            }

        }

    }
}
class AcountUnsafe implements Account
 {
     private Integer balance;

     public AcountUnsafe(Integer balance) {
         this.balance = balance;
     }

     @Override
     public Integer getBalance() {
         synchronized (this) {
             return this.balance;
         }
     }

     @Override
     public void withDraw(Integer account) {
         synchronized (this) {
             this.balance = -account;
         }
     }
 }

interface  Account {
    Integer getBalance();

    void withDraw(Integer account);

    static void demo(Account account1) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account1.withDraw(10);
            }));

        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
                    try {
                        t.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        long end = System.nanoTime();
        System.out.println(account1.getBalance() + "cost:" + (end - start) / 1000_000 + "ms");

    }
}