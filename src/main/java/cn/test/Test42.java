package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author:Summer
 * @Date:2020/9/22 09:41
 * @Description:
 */
@Slf4j(topic="c.test42")
public class Test42
{
    public static void main(String[] args) {
        AtomicStampedReference<String>ref=new AtomicStampedReference<>("A",0);
        log.debug("main start");
        String prev=ref.getReference();
        int mstamp=ref.getStamp();
        log.debug("版本号为:{}",mstamp);
        new Thread(()->{
            int stamp=ref.getStamp();
            log.debug("change A->B {}",ref.compareAndSet("A","B",stamp,stamp+1));
            log.debug(stamp+"");//这一个还是原来的版本号
            log.debug("no sth unknown");
            stamp=ref.getStamp();//获取新的版本号值
            log.debug(stamp+"");
            log.debug("change B->A {}",ref.compareAndSet("B","A",stamp,stamp+1));
            log.debug(stamp+"");





        }).start();
    }

}
