package com.offcn.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class duxiesuo {




//读写锁。同一时刻只能有一个线程进行数据的写操作。但是可以有多个线程进行数据的读操作
    //使用了readwritelock

        public static void main(String[] args) {

            com.offcn.Test.ReadAndWriteLock1 lock = new com.offcn.Test.ReadAndWriteLock1();
            for (int i = 1; i<6;i++){final int temp = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lock.put(temp+"",temp);
                    }
                },i+"").start();
            }

            for (int i = 1; i<6;i++){final int temp = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lock.get(temp+"");
                    }
                },i+"").start();
            }
        }
    }


class ReadAndWriteLock1{
    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();


    //写入法
    public void put(String key,Object value){
        rwLock.writeLock().lock();try{
            System.out.println(Thread.currentThread().getName()+" 正在写入！！！！"+key);
            map.put(key,value); try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  写入完成！！！！！"+key);}catch (Exception e){}finally {
            rwLock.writeLock().unlock();}

    }

    //读取方法
    public void get(String key) {rwLock.readLock().lock();try{
        System.out.println(Thread.currentThread().getName() + "  正在读取！！！"+key);
        Object o = map.get(key); try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "读取完成！！！！！"+key);}catch (Exception e){}finally{rwLock.readLock().unlock();}
    }
}

