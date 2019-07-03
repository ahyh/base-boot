package com.yh.java8.stream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * 构建流的几种方式
 *
 * @author yanhuan
 */
public class BuildStream {

    /**
     * 使用数组创建流
     *
     * @param wordList 字符串数组
     */
    private static void buildStream(String... wordList) {
        Stream.of(wordList).forEach(System.out::println);
    }

    /**
     * 使用集合创建流
     *
     * @param collection 输入的集合
     */
    private static void buildStream(Collection collection) {
        collection.stream().forEach(System.out::println);
    }

    /**
     * 使用数组创建流
     *
     * @param arrays 数组
     * @param <T>    泛型类型
     */
    private static <T> void buildStream(T[] arrays) {
        Arrays.stream(arrays).forEach(System.out::println);
    }

    /**
     * 通过文件来获取流
     *
     * @param filePath 文件的路径
     */
    private static void buildStream(String filePath) throws Exception {
        //获取文件中单词出现的个数
        long count = Files.lines(Paths.get(filePath), Charset.defaultCharset())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
        System.out.println(count);

        //通过文件来获取流，获取文件的每一行
        Files.lines(Paths.get(filePath), Charset.defaultCharset())
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 生成流，可以生成一个无限流
     */
    private static void buildStream() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 根据函数生成流，可以生成一个无限流
     *
     * @param init     初始值
     * @param operator 迭代函数
     * @param <T>      泛型类型
     */
    private static <T> void buildStream(T init, UnaryOperator<T> operator) {
        Stream.iterate(init, operator).limit(10).forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        buildStream("hello", "world");
        buildStream(Arrays.asList("hello", "world", "java8"));
        buildStream(Arrays.asList());
        Integer[] nums = {1, 2, 3, 4, 5, 6};
        buildStream(nums);

        buildStream("F:\\test\\000.txt");

        buildStream();

        buildStream(1, n -> n + 2);
    }
}
