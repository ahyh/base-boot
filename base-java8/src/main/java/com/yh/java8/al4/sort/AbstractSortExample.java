package com.yh.java8.al4.sort;

/**
 * sort相关算法的抽象类
 *
 * @author yanhuan
 */
public abstract class AbstractSortExample<T extends Comparable> {

    public abstract void sort(T[] array);

    public boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public void exchange(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void show(T[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
