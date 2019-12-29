package com.offcn.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TestSpin {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //自旋锁，使用的atomicReference 如果有线程占用了该锁，他就会在这里判断该线程是否解除了对锁的占用
    //如果解除了就获取该锁，反之就继续进行判断！！使用的while的循环进行判断
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"   is coming!!!!");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"   is died  = = ");
    }

    public static void main(String[] args) throws InterruptedException {
        TestSpin spin = new TestSpin();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spin.myLock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spin.myUnlock();
            }
        },"ti").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                spin.myLock();
                spin.myUnlock();
            }
        },"tq").start();
    }
}




