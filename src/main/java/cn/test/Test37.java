package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/19 15:42
 * @Description:
 *                                                 有序性
 *           重排指令优化可以使用volatile可以在变量之前禁止重排序：现代的处理器会设计为一个时钟周期完成一条执行时间最长的cpu指令，可以想得到指令还可以再划分为一个更小的阶段，例如，每一条指令都可以
 *                        分为一个更加小的阶段，每一条指令都可以分为：取指令，指令译码 执行指令 内存访问 数据写回
 *
 *             volitale原理
 *                     底层实现原理就是内存屏障
 *                     对volatile的变量写指令之后会加入写屏障
 *                     对volatile的变量读指令之后会加入读屏障
 *              首次访问会同步才会加锁
 *                           即使加锁了，jvm也可以对同步代码块内指令进行优化，执行顺序可能会打乱
 *
 */
@Slf4j(topic="c.test37")
public class Test37 {
    int num=0;
    boolean ready=false;
    public static void main(String[] args) {
    }
}
