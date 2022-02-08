package com.yh.java8.al4.sort;

/**
 * 插入排序
 *
 * @author yanhuan
 */
public class ShellSort<T extends Comparable> extends AbstractSortExample {

    @Override
    public void sort(Comparable[] array) {
        int len = array.length;
        int h = 1;
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            //将数组变成h有序
            for (int i = h; i < len; i++) {
                // 将array[i] 插入到 array[i -h], array[i-2*h], array[i- 3*h]之中
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    exchange(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] array = {3, 21, 5, 7, 8, 2, 54, 7, 8, 12};
        ShellSort<Integer> selectSort = new ShellSort<>();
        selectSort.sort(array);
        selectSort.show(array);
    }

}
