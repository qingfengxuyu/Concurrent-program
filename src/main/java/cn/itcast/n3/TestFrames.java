package cn.itcast.n3;
/*
     线程的上下文切换：保存当前线程的状态，
        线程的时间片用完
        垃圾回收
        有更加高级的线程要执行
        线程自己调用了sleep yield  wait join park synchronized lock等方法

      线程中的常见方法
        start 启动新线程，运行run方法中的代码，让线程处于就绪状态，里面的代码不一定立刻运行，每一个线程对象的run方法只能调用一次，调用多次就会出现IlegalThreadStateExction
        run   如果传递Runnable参数，则线程启动之后调用Runnable中run方法，否者默认不执行任何操作，可以创建thread子类对象来覆盖默认行为
        join  等待线程运行结束
        join（n） 最多等待线程在n毫秒内结束
        getId   获取线程长整型的id
        getName 获取线程名
        setName 修改线程名
        getPriority（）setPriority（int）获取或者修改线程的优先级优先级为1-10的整数，优先级越高，被cpu调度的机会越大，
        getState（） java线程状态是使用6个enum表示，分别为NEW RUNNABLE BLOCKED WAITING  TIMED_WAITING TERMINATED
        isInterrupted  判断线程是否被打断，有一个打断标记，不会清除打断标记
        isAlive 判断线程是否存活
        interrupted  static 打断线程，如果打断的线程正处在 sleep wait join会导致被打断的线程抛出InterrruptedExcption异常，并且会清除打断标记
                            如果打断正在运行的线程会设置打断标记，park的线程被打断也会设置打断标记
         currentThread（） static  获取当前正在执行的线程
         sleep（long） static 线程休眠，将cpu时间片给被人
         yield（）    static  提示线程调度器让出当前线程对cpu的使用        主要是为了测试和调试



 */
public class TestFrames {
    public static void main(String[] args) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                method1(20);
            }
        };
        thread.setName("t1");
        thread.start();
        method1(10);
    }
    public  static  void  method1(int x)
    {
        int y=x+1;
        Object m=method2();
        System.out.println(m);
    }
    public  static  Object method2()
    {
        Object o=new Object();
        return o;
    }
}
