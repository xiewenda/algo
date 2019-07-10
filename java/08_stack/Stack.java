import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 阿里面试题 下边程序大部分情况下执行没有问题 如果有问题是什么问题 根源是什么？
 *
 * 嵌套锁 当pop方法wait后 释放list锁 而没有释放方法上的锁也就对象锁 导致死锁
 */
public class Stack {
    LinkedList list = new LinkedList();
    /*public  void push(Object x){
        synchronized (this){
            list.addLast(x);
            System.out.println(Thread.currentThread().getName()+":push....."+x);
            notifyAll();
        }
    }
    public  Object pop() throws Exception{
        synchronized (this){
            while (list.size()<=0){
                System.out.println(Thread.currentThread().getName()+":pop.....wait");
                wait();
            }
            Object o = list.removeLast();
            System.out.println(Thread.currentThread().getName()+":pop....."+o);
            Thread.sleep(2000);
            return o;
        }
    }*/
    public synchronized void push(Object x){
        synchronized (this){
            list.addLast(x);
            System.out.println(Thread.currentThread().getName()+":push....."+x);
            notify();
        }
    }
    public synchronized Object pop() throws Exception{
        synchronized (this){
            if (list.size()<=0){
                System.out.println(Thread.currentThread().getName()+":pop.....wait");
                wait();
            }
            Object o = list.removeLast();
            System.out.println(Thread.currentThread().getName()+":pop....."+o);
            //Thread.sleep(2000);
            TimeUnit.SECONDS.sleep(2);
            return o;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        Runnable a = new test(stack);
        Runnable aa =  new test2(stack);
        for (int i = 0; i <100 ; i++) {
            if(i%3==0){
              new Thread(a).start();
            }else {
              new Thread(aa).start();
            }
        }
    }


}

class test implements Runnable{
    private  Stack stack;
    public test(Stack stack){
        this.stack=stack;
    }

    @Override
    public void run() {
            stack.push(Thread.currentThread().getName());
    }
}

class test2 implements Runnable{
    private  Stack stack;
    public test2(Stack stack){
        this.stack=stack;
    }

    @Override
    public void run() {
        try {
            stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}