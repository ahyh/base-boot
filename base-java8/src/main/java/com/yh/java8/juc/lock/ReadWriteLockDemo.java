package com.yh.java8.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo
 *
 * @author yanhuan
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        for (int j = 0; j < 10; j++) {
            new Thread(() -> shareData.putSafe(Thread.currentThread().getName(), "value" + Thread.currentThread().getName()), String.valueOf(j)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String value = shareData.getSafe(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "read, value is: " + value);
            }, String.valueOf(i)).start();
        }
    }

    static class ShareData {

        private volatile Map<String, String> map = new HashMap<>();

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        public void putNotSafe(String key, String value) {
            System.out.println(Thread.currentThread().getName() + "put data, key is " + key);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "put data finish, key is " + key);
        }

        public String getNotSafe(String key) {
            System.out.println(Thread.currentThread().getName() + "get data, key is " + key);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "get data finish, key is " + key);
            return value;
        }

        public void putSafe(String key, String value) {
            writeLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "put data, key is " + key);
                TimeUnit.SECONDS.sleep(2);
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "put data finish, key is " + key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }

        public String getSafe(String key) {
            readLock.lock();
            String value = "";
            try {
                System.out.println(Thread.currentThread().getName() + "get data, key is " + key);
                TimeUnit.SECONDS.sleep(2);
                value = map.get(key);
                System.out.println(Thread.currentThread().getName() + "get data finish, key is " + key);
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
            return value;
        }
    }


}

