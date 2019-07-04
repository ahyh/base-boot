package com.yh.java8.completable.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * CompletableFuture使用
 *
 * @author yanhuan
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
//        testCompletableFuture();
//        findPricesSync();
//        findPricesAsync();
        findPricesAsyncCompletable();
    }


    private static void testCompletableFuture() {
        Shop shop = new Shop();
        long start = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsync2("Apple");
        System.out.println("Future time:" + (System.currentTimeMillis() - start) + "ms");
        try {
            Double price = future.get();
            System.out.println("Price is:" + price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total Time:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findPricesSync() {
        long start = System.currentTimeMillis();
        List<Shop> list = Arrays.asList(new Shop("s1"), new Shop("s2"), new Shop("s3"), new Shop("s4"));
        String product = "Apple";
        List<String> prices = list.stream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findPricesAsync() {
        long start = System.currentTimeMillis();
        List<Shop> list = Arrays.asList(new Shop("s1"), new Shop("s2"), new Shop("s3"), new Shop("s4"));
        String product = "Apple";
        List<String> prices = list.parallelStream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findPricesAsyncCompletable() {
        long start = System.currentTimeMillis();
        List<Shop> list = Arrays.asList(new Shop("s1"), new Shop("s2"), new Shop("s3"), new Shop("s4"));
        String product = "Apple";
        List<CompletableFuture<String>> priceFutures = list.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        List<String> prices = priceFutures.stream().map(future -> future.join()).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }


}
