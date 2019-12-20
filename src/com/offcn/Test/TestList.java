package com.offcn.Test;

/**
 * 递归锁，又名可重入锁；
 */
class phone{

    public synchronized  void sendSMS()throws  Exception{
        System.out.println( Thread.currentThread().getId()+"\t invoked sendSMS()");
        sendEmain();
    }

    public synchronized  void sendEmain()throws  Exception{
        System.out.println( Thread.currentThread().getId()+"\t ####invoked sendEmain()");
    }
}

public class TestList {

    public static void main(String[] args) {

        phone phone = new phone();
      new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();


    }


}
