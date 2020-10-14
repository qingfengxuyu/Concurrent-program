package cn.itcast.n4;

/**
 * @Author:Summer
 * @Date:2020/9/16 09:31
 * @Description:
 */

public class Dog {
    private int age;

    public Dog(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog() {
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
