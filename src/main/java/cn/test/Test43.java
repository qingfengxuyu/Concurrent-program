package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @Author:Summer
 * @Date:2020/9/22 10:30
 * @Description:
 */
@Slf4j(topic="c.topic")
public class Test43 {
    public static void main(String[] args) throws InterruptedException {


        GarbageBag garbageBag = new GarbageBag("装满了垃圾");
        AtomicMarkableReference<GarbageBag> ref = new AtomicMarkableReference<>(garbageBag, false);
        log.debug("start...");
        GarbageBag prev=ref.getReference();
        log.debug(prev.toString());
        Thread.sleep(1);
        log.debug("想换一只新的垃圾袋");
        boolean success=ref.compareAndSet(prev,new GarbageBag("空垃圾袋"),false,true);
        log.debug("换了吗 {}",success);
        log.debug(ref.getReference().toString());

    }
}
class GarbageBag
{
    String desc;
    public GarbageBag(String desc)
    {
        this.desc=desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "GarbageBag{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
