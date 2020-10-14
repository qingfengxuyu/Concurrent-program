package cn.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Summer
 * @Date:2020/9/16 11:10
 * @Description:
 */
@Slf4j(topic = "c.test")
public class Test22 {
    static final  Object objecr=new Object();//可以保证引用不会再替换，这样前后锁定的都是一个对象

    public static void main(String[] args) {
         log.debug("ds");
    }
}
