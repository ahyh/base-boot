package com.yh.java8.al4.sort;

/**
 * 归并排序
 *
 * @author yanhuan
 */
public class MergeSort<T extends Comparable> extends AbstractSortExample {

    private Comparable[] aux;

    @Override
    public void sort(Comparable[] array) {
        aux = new Comparable[array.length];
        sort(array, 0, array.length-1);
    }

    private void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(array, lo, mid);
        sort(array, mid + 1, hi);
        merge(array, lo, mid, hi);
    }

    public void merge(Comparable[] array, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > hi) {
                array[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {3, 21, 5, 7, 8, 2, 54, 7, 8, 12};
        MergeSort<Integer> selectSort = new MergeSort<>();
        selectSort.sort(array);
        selectSort.show(array);
    }

}
