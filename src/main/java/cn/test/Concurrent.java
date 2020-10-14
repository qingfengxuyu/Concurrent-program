package cn.test;

/**
 * @Author:Summer
 * @Date:2020/9/25 18:50
 * @Description:
 *                       JUC线程安全类
 *                       Blocking 大部分基于锁，并提供来阻塞的方法
 *                       CopyOnWrite之类容器修改开销比较重（修改时使用拷贝，合适与读多写少）
 *                       Concurrent
 *                          内部很多操作使用cas操作，一般可以提供比较高的吞吐量
 *                          弱一致性
 *                              遍历时弱一致性，当利用迭代器遍历时，如果容器的发生修改，迭代器仍然可以迭代遍历，这时候的内容是旧的
 *                              大小弱一致性
 *                              读取弱一致性
 *                              遍历时如果发生了修改，对于非安全容器来讲，使用fail 机制也就是让遍历立刻失败，抛出ConcurrentModificationException
 *
 *
 */
public class Concurrent {
}
