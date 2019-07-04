package com.yh.java8.completable.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * CompletableFuture使用
 *
 * @author yanhuan
 */
public class CompletableFutureDemo {

    private static final List<Shop> shops = Arrays.asList(new Shop("s1"), new Shop("s2"), new Shop("s3"), new Shop("s4"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) throws Exception {
//        testCompletableFuture();
//        findPricesSync();
//        findPricesAsync();
//        findPricesAsyncCompletable();
//        findPricesAsyncWithExecutor();
//        findPricesByGetPrice2();
//        findPricesByGetPrice2Completable();
        MergeCompletableFuture();
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
        String product = "Apple";
        List<String> prices = shops.stream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findPricesAsync() {
        long start = System.currentTimeMillis();
        String product = "Apple";
        List<String> prices = shops.parallelStream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void findPricesAsyncCompletable() {
        long start = System.currentTimeMillis();
        String product = "Apple";
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        List<String> prices = priceFutures.stream().map(future -> future.join()).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }


    private static void findPricesAsyncWithExecutor() {
        long start = System.currentTimeMillis();
        String product = "Apple";
        List<CompletableFuture<String>> priceFutures = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %s", shop.getName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());
        List<String> prices = priceFutures.stream().map(future -> future.join()).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 同步的阻塞时间长
     */
    private static void findPricesByGetPrice2() {
        long start = System.currentTimeMillis();
        List<String> prices = shops.stream().map(shop -> shop.getPrice2("Apple"))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 多个Future合并，有依赖关系，相比较于上一个方法快很多
     */
    private static void findPricesByGetPrice2Completable() {
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> futureList = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2("Apple"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        List<String> prices = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        prices.stream().forEach(System.out::println);
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 两个CompletableFuture的合并
     */
    private static void MergeCompletableFuture() throws Exception {
        Shop s5 = new Shop("s5");
        long start = System.currentTimeMillis();
        CompletableFuture<Double> apple = CompletableFuture.supplyAsync(() -> s5.getPrice("Apple"))
                .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)), (price, rate) -> price * rate);
        System.out.println(apple.get());
        System.out.println("TotalTime:" + (System.currentTimeMillis() - start) + "ms");
    }


}
