package com.yh.java8.al4.sort;

/**
 * 选择排序
 * 首先找到数组中最小的那个元素，然后将它和数组的第一个元素交换位置
 * 再次，在剩下的元素中找到最小的元素，将它与数组中第二个元素交换位置
 * 重复上述步骤，直到将整个数组排序
 *
 * @author yanhuan
 */
public class SelectSort<T extends Comparable> extends AbstractSortExample {

    @Override
    public void sort(Comparable[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            if (i != min) {
                exchange(array, i, min);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {3, 21, 5, 7, 8, 2, 54, 7, 8, 12};
        SelectSort<Integer> selectSort = new SelectSort<>();
        selectSort.sort(array);
        selectSort.show(array);
    }

}
