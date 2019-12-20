package com.offcn.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    /**
     * 手写一个自旋锁
     */
   AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        //是进来的进程
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+" is comeing");
        //如果当前为null，也就是没有线程进入，该线程就可以进去，会返回true，然后进行取反
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        //获取当前进入的线程
        Thread thread = Thread.currentThread();
        //将该线程给释放 变为null
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+" is leave = 。= ");

    }

    public static void main(String[] args) {


        SpinLockDemo lockDemo = new SpinLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {

                lockDemo.myLock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockDemo.myUnLock();
            }
        },"t1").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                lockDemo.myLock();

                lockDemo.myUnLock();
            }
        },"t2").start();
    }


}
