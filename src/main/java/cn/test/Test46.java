package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Summer
 * @Date:2020/9/22 12:01
 * @Description:
 *                             cpu核心
 *                            每一个核心都有 一级缓存，二级缓存，每一个cpu核心之间共享三级缓存
 *                            三级缓存之下才是内存
 *                            从 cpu到                               大约需要的时钟周期
 *                            寄存器                                  1 cycle（40Ghz CPU约为0.25ns)
 *                            L1                                     3-4 cycle
 *                            L2                                      10-20 cycle
 *                            L3                                      40-50cycle
 *                            内存                                      120-240 cycle
 *                            因为Cpu与内存的速度差异很大，需要靠预读数据到缓存来提升效率
 *                            缓存是以缓存行为单位，每一个缓存行对应着一块内存，一般是64byte（8个long）
 *                            缓存的加入会造成数据副本的产生，即同一份数据会缓存在不同核心的缓存行中
 *                            cpu要保证数据的一致性，如果某一个cpu的核心更改了数据，其他cpu核心对应的整个缓存必须失败
 *                            缓存伪共享
 *
 *
 */
@Slf4j(topic="c.test")
public class Test46 {
    private AtomicInteger state=new AtomicInteger(0);
    public void lock()
    {
        while (true)
        {
            if(state.compareAndSet(0,1))
            {
                break;
            }
        }

    }
    public void unlock()
    {
        log.debug("unlock");
        state.set(0);
    }

    public static void main(String[] args) {
        Test46 test46=new Test46();
        new Thread(()->{
            log.debug("begin..");
            test46.lock();
            try
            {
                log.debug("lock...");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finally {
                test46.unlock();
            }
        }).start();
        new Thread(()->{
            log.debug("begin..");
            test46.lock();
            try
            {
                log.debug("lock...");
            }
            finally {
                test46.unlock();
            }
        }).start();
    }
}
