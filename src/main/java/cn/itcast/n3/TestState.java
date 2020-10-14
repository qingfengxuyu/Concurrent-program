package cn.itcast.n3;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/11 18:24
 * @Description:
 *               从操作系统层面：线程具有五种状态
 *  *       初始状态   语言层面创建了线程对象，没有与操作系统关联
 *  *       可运行状态  线程对象已经被创建（与操作系统线程关联：或者说是就绪状态）
 *  *       运行状态   cpu时间片运行中的状态
 *  *           当前时间片使用完之后，会从运行状态转到可运行状态，导致线程的上下文切换
 *  *       阻塞状态 如果调用了阻塞API,如 BIO读写文件，这时线程实际上不会使用的cpu，会导致线程上下文切换
 *  *                 等BIO操作完毕，会由操作系统唤醒阻塞的线程，转换至可运行状态，阻塞状态可以使得线程的执行不再cpu的考虑范围之内，也不会
 *  *                 立即进入可执行状态，可以更好的维护线程的完整性（另一个线程正在执行，还有时间片，你进入执行状态，那他怎么办）
 *  *       终止状态   线程执行结束，生命周期已经结束，不再会切换到其他的状态
 *  *                         从java API层面来描述：线程具有六种状态
 *  *       new（相当于初始状态） 就是没有调用start方法
 *          runnable(相当于阻塞状态加锁执行状态）
 *          blocked（同步锁导致的阻塞状态一）
 *          waiting（join方法导致不确定时间阻塞状态二）
 *          timed-waiting（sleep方法导致的明确阻塞时间的阻塞状态三）
 *          termination （终止状态）
 *                注意：java不能确定线程的可运行状态，所以没有这种状态
 */
@Slf4j(topic="c.testState")
public class TestState {
    public static void main(String[] args) {
        //没有创建线程对象
        Thread t1=new Thread("t1"){
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        Thread t2=new Thread("t2"){
            @Override
            public void run() {
                while(true)
                {

                }
            }
        };
        t2.start();
        Thread t3=new Thread("t3"){
            @Override
            public void run() {
                log.debug("runing");
            }
        };
        t3.start();
        Thread t4=new Thread("t4"){
            @Override
            public void run() {
                synchronized (TestState.class)
                {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();
        Thread t5=new Thread("t5"){
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {

                }
            }
        };
        t5.start();
        Thread t6=new Thread("t6"){
            @Override
            public void run() {
                synchronized (TestState.class)
                {
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {//t3线程可以马上执行完毕，让主线程阻塞一段时间，可以得到t3运行结束之后的状态

        }
        log.debug("t1 {}",t1.getState());
        log.debug("t2 {}",t2.getState());
        log.debug("t3 {}",t3.getState());
        log.debug("t4 {}",t4.getState());
        log.debug("t5 {}",t5.getState());
        log.debug("t6 {}",t6.getState());
    }

}
