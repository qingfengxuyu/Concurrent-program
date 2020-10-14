package cn.test;

/**
 * @Author:Summer
 * @Date:2020/9/11 12:43
 * @Description:
 *          防止cpu占用100%
 *           在没有利用cpu计算时，不要让while（true）空转浪费cpu，这时候可以使用yield或者是sleep来控制使用权
 */
public class Test9 {
    public static void main(String[] args) {
        new Thread()
        {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
