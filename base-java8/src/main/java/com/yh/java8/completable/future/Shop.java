package com.yh.java8.completable.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 测试CompletableFuture的商店类
 *
 * @author yanhuan2
 */
public class Shop {

    private String name;

    public Shop(){
    }

    public Shop(String name){
        this.name = name;
    }

    private static final Random random = new Random();

    /**
     * 获取产品的价格
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 异步获取产品价格的方法
     * 第一版：通过原生的Thread类来启动一个线程
     */
    public Future<Double> getPriceAsync1(String product) {
        CompletableFuture<Double> priceFuture = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            priceFuture.complete(price);
        }).start();
        return priceFuture;
    }

    /**
     * 异步获取产品价格方法
     * 第二版：通过CompletableFuture的工厂方法来获取
     */
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    /**
     * 模拟阻塞1秒后查询到产品的价格
     */
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟延迟的方法
     */
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
