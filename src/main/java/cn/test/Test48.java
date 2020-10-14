package cn.test;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author:Summer
 * @Date:2020/9/22 14:47
 * @Description:
 *                             Unsafe对象
 *
 */
public class Test48 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
       Field theUnsafe= Unsafe.class.getDeclaredField("theUnsafe");
       theUnsafe.setAccessible(true);
       Unsafe unsafe=(Unsafe)theUnsafe.get(null);
        System.out.println(unsafe);
        //获取域的偏移地址
        long idOffset= unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameoffset=unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));
        Teacher teacher=new Teacher();
        unsafe.compareAndSwapInt(teacher,idOffset,0,1);
        unsafe.compareAndSwapObject(teacher,nameoffset,null,"张三");
        System.out.println(teacher);

    }
}
@Data
class Teacher
{
    volatile int id;
    volatile String name;
}
