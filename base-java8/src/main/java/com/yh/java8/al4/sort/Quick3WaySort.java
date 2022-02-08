package com.yh.java8.al4.sort;

/**
 * 快速排序
 *
 * @author yanhuan
 */
public class Quick3WaySort<T extends Comparable> extends AbstractSortExample {

    @Override
    public void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exchange(a, lt++, i++);
            } else if (cmp > 0) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] array = {3, 21, 5, 7, 8, 2, 54, 7, 8, 12};
        Quick3WaySort<Integer> selectSort = new Quick3WaySort<>();
        selectSort.sort(array);
        selectSort.show(array);
    }

}
