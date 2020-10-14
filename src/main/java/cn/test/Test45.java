package cn.test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author:Summer
 * @Date:2020/9/22 11:24
 * @Description:
 *                                                         字段更新器
 *                           AtomicReferenceFieldUpdater//域字段
 *                           AtomicIntergerFeildUpdater
 *                           AtomicLongFiledUpdater
 *                           利用字段更新器，可以针对对象的某一个域（Filed）进行原子操作，只能配合Volatile使用
 *
 */
public class Test45 {
    public static void main(String[] args)
    {
        Student student=new Student();
        AtomicReferenceFieldUpdater fieldUpdater;
        fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        boolean b=fieldUpdater.compareAndSet(student,null,"张三");
        System.out.println(student.toString());
    }
}
class  Student
{
   volatile   String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
