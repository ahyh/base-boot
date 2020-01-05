package com.yh.utils;

import com.yh.java8.datastructure.SparseArrayUtil;
import org.junit.Test;

public class SparseArrayUtilTest {

    private static int[][] array = new int[11][11];

    private static int[][] sparseArray = new int[3][3];

    static {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                array[i][j] = 0;
            }
        }
        array[1][2] = 1;
        array[2][3] = 2;
    }

    static {
        sparseArray[0] = new int[]{11, 11, 2};
        sparseArray[1] = new int[]{1, 2, 1};
        sparseArray[2] = new int[]{2, 3, 2};
    }

    @Test
    public void printTwoDimensionArray() {
        SparseArrayUtil.printTwoDimensionArray(array);
    }

    @Test
    public void testTwoDimenosion2SparseArray() {
        int[][] ints = SparseArrayUtil.twoDimensionArray2SparseArray(array);
        SparseArrayUtil.printTwoDimensionArray(ints);
    }

    @Test
    public void testSparseArray2TwoDimensionArray() {
        int[][] ints = SparseArrayUtil.sparseArray2TwoDimensionArray(sparseArray);
        SparseArrayUtil.printTwoDimensionArray(ints);
    }
}
