package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @Author:Summer
 * @Date:2020/9/16 18:50
 * @Description:
 */
@Slf4j(topic="c.test24")
public class Test24 {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);//lamda表达式
        for (int i = 0; i < 3; i++) {
            int id=i;
            new Thread(() -> {
               messageQueue.put(new Message(id,"值"+id));



            }).start();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = messageQueue.take();
            }
        }).start();
    }

}
@Slf4j(topic="c.test24")
class MessageQueue
{
    private LinkedList<Message>linkedList=new LinkedList<>();
    private  int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    public Message take() {
        //检查队列是否为空
        synchronized (linkedList) {
            while (linkedList.isEmpty()) {
                try {
                    log.debug("队列为空，消费者线程等待");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message=linkedList.removeFirst();
            log.debug("已经消费消息 {}",message);
               linkedList.notifyAll();

                return message;
        }
    }
    public  void put(Message message)
    {
        synchronized (linkedList)
        {
            while (linkedList.size()==capcity)
            {
                try {
                    log.debug("队列已经满了，生产者线程等待");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            linkedList.addLast(message);
            log.debug("已经生产消息{}",message);
            linkedList.notifyAll();
        }
    }
}
@Slf4j(topic="c.test24")
final class Message//只使用get方法，只能在创建对象的时候初始化，保证了线程安全性，如果对象设计为final类就会不可以创建子类
{
    private int id;
    private  Object value;

    public Message(int id,Object value) {
        this.id = id;
        this.value=value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}

