package cn.test;
/*
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author:Summer
 * @Date:2020/9/22 10:45
 * @Description:

public class Test44 {
    public static void main(String[] args) {
        demo(()->new int[10],
                (arry)->arry.length,
                (array,index)->array[index++],
                array->System.out.println(Arrays.toString(array))
        );
    }

       函数式接口
         supplier  提供者，无中生有，（）->有返回值
         function 函数，一个参数一个接口  （参数）->结果  BigFunction          (参数一，参数二）->结果
         consumer 消费者 一个参数没结果 （参数） void   Biconsumer （两个参数）->没有结果





    private static<T> void  demo(

            Supplier<T>supplier,
            Function<T, Integer>lengthFun,
            BiConsumer<T,Integer> putComsumer,
            Consumer<T>printComsumer
            )
    {
        List<Thread>ts=new ArrayList<>();
        T arry=supplier.get();
        int length=lengthFun.apply(arry);
        for (int i = 0; i < length; i++)
        {
            ts.add(new Thread()->
            {
                for (int j = 0; j <10000 ; j++)
                {
                    putComsumer.accept(arry,j%length);
                }
            }));
        }
        ts.forEach(t->t.start());
        ts.forEach(t->{...});
        printComsumer.accept(arry);
    }
}

   */