package cn.itcast.n4;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;


/**
 * @Author:Summer
 * @Date:2020/9/12 11:43
 * @Description:
 *               方法的访问权限修饰符 private，只能在类中的其他方法调用，创建对象时，使用对象.方法是无法调用的，这种情况下，局部变量是线程安全的，public也是没有问题的
 *               private 和final增强了线程的安全性
 *                                            常见的线程安全类
 *
 *               String
 *               Integer
 *               StringBuffer
 *               Random
 *               Vector
 *               Hashtable
 *               java。util。concurrent包下的类
 *                这里说的线程安全说的是：多个线程调用它们同一个实例的某一个方法的时候是线程安全的，也可以理解为：
 *                    它们的每一个方法是原子的，多线程可以读，不可以改
 *                    但是注意它们多个方法的组合不是原子的
 *               所有的子类继承httpservlet这个类，都是在tomcat中运行，在tomcat中只有一份实例，实例的信息被共享，具有线程安全问题
 *               在spring容器中，一般都是使用单例模式，也是线程不安全的，前置通知和后置通知可以使用环绕通知：做成局部变量，
 */
@Slf4j(topic="c.TestThreadSafe")
public class TestThreadSafe {
    static final  int  LOOP_NUMBER=200;
    static final int THREAD_NUMBER=2;
    public static void main(String[] args) {
        ThreadUnsafe unsafe=new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(()->{
                unsafe.method1(LOOP_NUMBER);
            },"thread"+(i+1)).start();
        }
    }
}
class ThreadUnsafe
{
       ArrayList<String>list=new ArrayList<>();//成员变量
       public void method1(int loopNumber)
       {
           for (int i = 0; i < loopNumber; i++) {
               method2();
               method3();
           }
       }
       private  void method2(){list.add("1");}
       private void  method3(){list.remove(0);}
}
class Threadsafe
{
     private int n;
    public void method1(int loopNumber)
    {
        ArrayList<String>list=new ArrayList<>();/*局部变量归method1方法，即使在这个方法内调用别的方法，别的方法也不能使用这个局部变量，需要传递地址给调用的方法
        两个对象都会创建新的对象

        */
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }
    public  void method2(ArrayList<String>list){list.add("1");}//如果方法和变量是私有的，则子类无法继承和重写方法，也无法继承私有变量
    private void  method3(ArrayList<String>list){list.remove(0);}
}

class ThreadSafeSubClass extends  Threadsafe//继承线程安全的子类，但是由于子类重写了方法，增加了新线程，这种情况下会发生线程不安全的问题,可以增加final关键字，不能重写方法，即使设置为私有方法，也可以在子类起一个一模一样的名字
{

    public void method3(ArrayList<String> list) {
        new Thread(()->{list.remove(0);}).start();//私有成员变量也可以被多个线程共享，因此是线程不安全的
    }
}
