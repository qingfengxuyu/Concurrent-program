package cn.itcast.n4;

/**
 * @Author:Summer
 * @Date:2020/9/12 16:01
 * @Description:
 *                                                 Monitor概念
 *    java对象头
 *             32位虚拟机为例
 *              普通对象头  32bit mark word    32bit kclass word（指向类对象的指针）
 *              数组对象头 32bit  mark word    2bit kclass word（指向类对象的指针）  array length 32bit
 *
 *               举例说明：int 存储是4个字节32bit
 *                        Integer 一个对象头8个字节加上4个字节存储值一共12字节
 *
 *
 *              Monitor被翻译为监视器或者是管程（这个是归操作系统所有）
 *              每一个java对象都可以关联一个Monitor对象，如果使用synchronied给对象上锁（重量级）之后，该对象头的Mark word中就被设置为指向Monitor对象的指针
 *                过程：
 *                     1一个对象上锁，将这个对象头信息指向一个操作系统的监视器，监视器的结构如下
 *                                     WaitSet
 *                                     EntryList
 *                                     Owner 标记线程拥有者
 *                     2.其他线程查看对象锁是否上锁了，有了就把自己的信息放到EntryList（锁队列），进入阻塞状态，monitor将所有的阻塞线程唤醒，让它们竞争，
 *
 *              case成功：轻量级锁也是使用重量级锁关键词synchronied，jvm在判断多线程访问锁的时间顺序上是否有竞争，没有的话就锁记录的地址与mark word 的地址想交换，在栈帧中有一个锁记录（引用锁对象的地址，锁记录的地址，）
 *             case失败：如果其他线程已经持有了该object的轻量级锁，这时表明，进入锁膨胀过程
 *             如果是自己执行了synchronied锁重入，那么再添加一条Lock Record作为重入的计数
 *
 *            锁膨胀，进入重量级锁
 *                                                 自旋优化（使用多核cpu）
 *            重量级锁竞争的时候，还可以使用自旋进行优化，如果当前线程自旋优化成功（即同步代码块已经退出了同步块，释放了锁）这时当前线程就可以避免阻塞
 *
 *           线程1cpu上                               对象mark                        线程2（cpu上）
 *          访问同步块，获取monitor                   重量锁指针
 *          成功加锁
 *               如果自旋失败就进入阻塞状态
 *                                                 偏向锁（一般是使用在主线程，因为主线程只有一个线程使用偏向锁==》就他一个线程使用）
 *              轻量级锁没有竞争的时候（就自己这个线程），每一次重入仍需要CAS操作
 *              偏向机锁：只有第一次使用CAS将线程ID设置到对象的Mark Word头，之后发现这个线程id是自己就表示没有竞争，不用重新CAS，这个线程就归线程所有
 *
 *              偏向状态
 *                 一个对象创建的时候
 *                    如果开启了偏向锁（默认是开启的），那么对象创建之后，markword的值为Ox05即最后三位为101，这时它的thread，epoch，age都为0
 *                    偏向锁是默认延迟的，不会在程序启动之后立即生效，如果想避免延迟，可以加入VM参数 -xx：BasicedLockingStartUpDelepay=0来禁用延迟
 *                    如果没有开启偏向锁，那么对象创建之后，markword的值为Ox01，这时它的hashcode，age都为0，第一次用到hashcode
 *
 *               对象的偏向锁（如果对象.hashcode()使用了禁用偏向锁就）
 *
 *
 */
public class Monitor {
}
