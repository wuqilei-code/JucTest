package com.offcn.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    //Demo  假设今天需要开会，必须要等7个人到齐之后会议才能开始。那么先到的人员需要等着厚后到的人员到齐才能展开会议

    public static void main(String[] args) {
        CyclicBarrier daoqi = new CyclicBarrier(7, new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("人员到齐，开始开会！！！");
            }
        }, "daoqi"));

        for (int i= 1; i < 9;i++){
            final  int temp = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //该线程到达会议室之后就要等着其他的人员到达之后才能开会
                    System.out.println("第"+temp+"位经理到达会议室！！！");
                    try {
                        //等待其他人员到达
                        daoqi.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            },String.valueOf(temp)).start();
        }


    }




}
