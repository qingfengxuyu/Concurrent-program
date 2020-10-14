package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author:Summer
 * @Date:2020/9/24 15:22
 * @Description:   使用任务调度，定时每周四执行任务
 *
 */
@Slf4j(topic="c.testschedule")
public class TestSchedule {
    public static void main(String[] args) {
        //获取当前时间
        LocalDateTime now=LocalDateTime.now();
        System.out.println(now);
        //获取距离周四的时间
        LocalDateTime time=now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        System.out.println(time);
        if(now.compareTo(time)>0)
        {
            time.plusWeeks(1);
        }
        long i=Duration.between(now,time).toMillis();
        System.out.println(i);

    }
}
