package com.offcn.Test;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLunch {


    //测试countdownunch
    public static void main(String[] args) {

        //假设教室有6个人， 最后一个人走后班长负责关灯。构造方法中需要传入一个数字，表示的就是有几个学会
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i<7;i++){
            final int temp = i;
            //lmd表达式
            new Thread(()->{
                System.out.println("第"+temp+"位学生离开教室。");
               //每离开一位学生，就对这个数减一
                countDownLatch.countDown();
            },String.valueOf(temp)).start();
        }

        //这个是main线程的。 所以要等上面的同学都走光了之后才能执行这个操作需要将其awite
       try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("班长把灯关掉了~~~");


    }

}
