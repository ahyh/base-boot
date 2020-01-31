package com.yh.java8.datastructure.sort;

/**
 * 排序算法
 *
 * @author yanhuan
 */
public class Sort {

    private final static SortStrategy BUBBLE_SORT = new BubbleSort();
    private final static SortStrategy SELECT_SORT = new SelectSort();
    private final static SortStrategy INSERT_SORT = new InsertSort();
    private final static SortStrategy INSERT_SORT_BS = new InsertSortByBinarySearch();
    private final static SortStrategy SHELL_SORT = new ShellSort();
    private final static SortStrategy QUICK_SORT = new QuickSort();
    private final static SortStrategy MERGE_SORT = new MergeSort();
    private final static SortStrategy MERGE_SORT_RECURSE = new MergeSortRecurse();
    private final static SortStrategy HEAP_SORT = new HeapSort();


    public static <T extends Comparable> void bubbleSort(T[] elements) {
        BUBBLE_SORT.sort(elements);
    }

    public static <T extends Comparable> void selectSort(T[] elements) {
        SELECT_SORT.sort(elements);
    }

    public static <T extends Comparable> void insertSort(T[] elements) {
        INSERT_SORT.sort(elements);
    }

    public static <T extends Comparable> void insertSortBS(T[] elements) {
        INSERT_SORT_BS.sort(elements);
    }

    public static <T extends Comparable> void shellSort(T[] elements) {
        SHELL_SORT.sort(elements);
    }

    public static <T extends Comparable> void quickSort(T[] elements) {
        QUICK_SORT.sort(elements);
    }

    public static <T extends Comparable> void mergeSort(T[] elements) {
        MERGE_SORT.sort(elements);
    }

    public static <T extends Comparable> void mergeSortRecurse(T[] elements) {
        MERGE_SORT_RECURSE.sort(elements);
    }

    public static <T extends Comparable> void radixSort(T[] elements) {

    }

    public static <T extends Comparable> void heapSort(T[] elements) {
        HEAP_SORT.sort(elements);
    }

    /**
     * 基数排序
     */
    public static class RadixSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            Comparable[][] bucket = new Comparable[10][elements.length];
            int[] bucketEleCounts = new int[10];
            for (int i = 0; i < elements.length; i++) {

            }
        }
    }

    /**
     * 使用递归的方式实现归并排序
     */
    public static class MergeSortRecurse extends AbstractMergeSort {

        @Override
        public void sort(Comparable[] elements) {
            mergeSortRecurse(elements, 0, elements.length - 1);
        }

        public static void mergeSortRecurse(Comparable[] elements, int low, int high) {
            int mid;
            if (low < high) {
                mid = (low + high) / 2;
                mergeSortRecurse(elements, low, mid);
                mergeSortRecurse(elements, mid + 1, high);
                merge(elements, low, mid, high);
            }
        }
    }

    /**
     * 归并排序
     * O(n * log n)
     */
    public static class MergeSort extends AbstractMergeSort {

        @Override
        public void sort(Comparable[] elements) {
            int length;
            int n = elements.length;
            for (length = 1; length < n; length = 2 * length) {
                mergePass(elements, length, n);
            }
        }

        public static void mergePass(Comparable[] elements, int length, int n) {
            int i;
            for (i = 0; i + 2 * length - 1 < n; i = i + 2 * length) {
                merge(elements, i, i + length - 1, i + 2 * length - 1);
            }
            if (i + length - 1 < n) {
                merge(elements, i, i + length - 1, n - 1);
            }
        }
    }

    /**
     * 归并排序
     */
    public abstract static class AbstractMergeSort implements SortStrategy {

        /**
         * 一次归并
         *
         * @param elements 两个有序元素表构成的待排序的数组
         * @param low      第一个有序表的开始下标
         * @param mid      中间位置
         * @param high     最大的下标
         */
        public static void merge(Comparable[] elements, int low, int mid, int high) {
            Comparable[] temp = new Comparable[high - low + 1];
            int i = low, j = mid + 1, k = 0;
            while (i <= mid && j <= high) {
                if (elements[i].compareTo(elements[j]) < 0) {
                    temp[k] = elements[i];
                    k++;
                    i++;
                } else {
                    temp[k] = elements[j];
                    j++;
                    k++;
                }
            }
            while (i <= mid) {
                temp[k] = elements[i];
                i++;
                k++;
            }
            while (j <= high) {
                temp[k] = elements[j];
                j++;
                k++;
            }
            for (k = 0, i = low; i <= high; k++, i++) {
                elements[i] = temp[k];
            }
        }
    }

    /**
     * 快速排序
     */
    public static class QuickSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            sort(elements, 0, elements.length - 1);
        }

        public static void sort(Comparable[] elements, int s, int t) {
            Comparable temp;
            int i = s, j = t;
            if (s < t) {
                temp = elements[s];
                while (i != j) {
                    while (j > i && temp.compareTo(elements[j]) < 0) {
                        j--;
                    }
                    elements[i] = elements[j];
                    while (i < j && temp.compareTo(elements[i]) > 0) {
                        i++;
                    }
                    //ele[i] swap ele[j]
                    elements[j] = elements[i];
                }
                elements[i] = temp;
                sort(elements, s, i - 1);
                sort(elements, i + 1, t);
            }
        }
    }

    /**
     * 希尔排序
     */
    public static class ShellSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            int i, j, gap = elements.length / 2;
            Comparable temp;
            while (gap > 0) {
                for (i = gap; i < elements.length; i++) {
                    temp = elements[i];
                    j = i - gap;
                    while (j >= 0 && temp.compareTo(elements[j]) < 0) {
                        elements[j + gap] = elements[j];
                        j -= gap;
                    }
                    elements[j + gap] = temp;
                }
                gap = gap / 2;
            }
        }
    }

    /**
     * 二分查找插入排序
     */
    public static class InsertSortByBinarySearch implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            Comparable temp;
            int j, low, high, mid;
            for (int i = 1; i < elements.length; i++) {
                temp = elements[i];
                low = 0;
                high = i - 1;
                while (low <= high) {
                    mid = (low + high) / 2;
                    if (temp.compareTo(elements[mid]) < 0) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                for (j = i - 1; j >= high + 1; j--) {
                    elements[j + 1] = elements[j];
                }
                elements[high + 1] = temp;
            }
        }
    }

    /**
     * 直接插入排序
     * 把n个待排序的元素看成是一个有序表和一个无序表，开始时有序表中只有一个元素，无序表中包含n-1
     * 个元素，排序的过程中每次从无序表中取出第一个元素把它的排序吗一次与有序表元素的排序码进行比较，
     * 将它插入到有序表中的适当位置，使之成为新的有序表
     * O(n^2)
     */
    public static class InsertSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            Comparable temp;
            int j;
            for (int i = 1; i < elements.length; i++) {
                temp = elements[i];
                j = i - 1;
                //如果没有找到合适的位置，需要将已经排序好的元素后移
                while (j >= 0 && temp.compareTo(elements[j]) < 0) {
                    elements[j + 1] = elements[j];
                    j--;
                }
                elements[j + 1] = temp;
            }
        }
    }

    /**
     * 选择排序
     * 一共有n-1轮循环，每一轮也是一个循环，找出最小的并交换
     * O(n^2)
     */
    public static class SelectSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            Comparable min;
            int minIndex = 0;
            for (int i = 0; i < elements.length - 1; i++) {
                min = elements[i];
                for (int j = i + 1; j < elements.length; j++) {
                    if (min.compareTo(elements[j]) > 0) {
                        min = elements[minIndex];
                        minIndex = j;
                    }
                }
                min = elements[i];
                elements[i] = elements[minIndex];
                elements[minIndex] = min;
            }
        }
    }

    /**
     * 冒泡排序
     * 两轮循环，比较大小并交换
     * O(n^2), 平方阶
     */
    public static class BubbleSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            boolean flag = false;
            Comparable temp;
            for (int i = 0; i < elements.length; i++) {
                for (int j = i + 1; j < elements.length; j++) {
                    if (elements[i].compareTo(elements[j]) > 0) {
                        temp = elements[i];
                        elements[i] = elements[j];
                        elements[j] = temp;
                        flag = true;
                    }
                }
                //如果一次交换都没有，可以直接退出, 否则重置为false
                if (!flag) {
                    return;
                } else {
                    flag = false;
                }
            }
        }
    }

    /**
     * 堆排序
     * 1-将无序序列构建成一个堆，根据升序/降序需求选择大顶堆或者小顶堆
     * 2-将堆顶元素与末尾元素进行交换，将最大的元素沉入数组末端
     * 3-重新调整结构，使其满足堆定义，然后继续交换堆顶元素与末尾元素，反复执行调整与交换步骤，知道整个序列有序
     */
    public static class HeapSort implements SortStrategy {

        @Override
        public void sort(Comparable[] elements) {
            heapSort(elements, elements.length);
        }

        public void heapSort(Comparable[] elements, int length) {
            int i;
            Comparable temp;
            for (i = length / 2 - 1; i >= 0; i--) {
                adjustHeap(elements, i, length);
            }
            for (int j = length - 1; j > 0; j--) {
                //找到大顶堆后进行交换
                temp = elements[j];
                elements[j] = elements[0];
                elements[0] = temp;
                adjustHeap(elements, 0, j);
            }
        }

        /**
         * 调整堆，使之成为大顶堆或小顶堆
         *
         * @param elements 待排序的元素
         * @param low      待排序元素起始下标
         * @param high     待排序元素末尾下标
         */
        private static void adjustHeap(Comparable[] elements, int low, int high) {
            int i = low;
            Comparable temp = elements[i];
            for (int j = 2 * i + 1; j < high; j = j * 2 + 1) {
                if (j + 1 < high && elements[j].compareTo(elements[j + 1]) < 0) {
                    j++;
                }
                if (elements[j].compareTo(temp) > 0) {
                    elements[i] = elements[j];
                    i = j;
                } else {
                    break;
                }
            }
            elements[i] = temp;
        }
    }

    private interface SortStrategy<T extends Comparable> {
        void sort(T[] elements);
    }

}
