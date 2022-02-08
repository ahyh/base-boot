package com.yh.java8.al4.sort;

/**
 * 插入排序
 *
 * @author yanhuan
 */
public class InsertSort<T extends Comparable> extends AbstractSortExample {

    @Override
    public void sort(Comparable[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            // 将array[i]插入到a[i-1], a[i-2], a[i-3]...之中
            for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
                exchange(array, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {3, 21, 5, 7, 8, 2, 54, 7, 8, 12};
        InsertSort<Integer> selectSort = new InsertSort<>();
        selectSort.sort(array);
        selectSort.show(array);
    }

}
