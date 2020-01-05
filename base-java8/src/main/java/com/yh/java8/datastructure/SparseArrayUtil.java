package com.yh.java8.datastructure;

/**
 * 稀疏数组工具类
 *
 * @author yanhuan
 */
public class SparseArrayUtil {

    /**
     * 稀疏数组转换为二维数组
     *
     * @param sparseArray 稀疏数组
     * @return 原始二维数组
     */
    public static int[][] sparseArray2TwoDimensionArray(int[][] sparseArray) {
        int[][] twoDimensionArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            twoDimensionArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return twoDimensionArray;
    }

    /**
     * 将二维数组转换为稀疏数组
     *
     * @param twoDimensionArray 原始的二维数组
     * @return 稀释数组
     */
    public static int[][] twoDimensionArray2SparseArray(int[][] twoDimensionArray) {
        int values = 0;
        int row = twoDimensionArray.length;
        int col = twoDimensionArray[0].length;
        int[][] array = new int[][]{{row, col, 0}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (twoDimensionArray[i][j] != 0) {
                    int[] tempArr = {i, j, twoDimensionArray[i][j]};
                    //数组扩容
                    int[][] newArray = new int[array.length + 1][3];
                    System.arraycopy(array, 0, newArray, 0, array.length);
                    array = newArray;
                    array[++values] = tempArr;
                }
            }
        }
        array[0][2] = values;
        return array;
    }

    public static void printTwoDimensionArray(int[][] array) {
        if (array == null) {
            throw new RuntimeException("invalid array");
        }
        for (int[] arr : array) {
            for (int ele : arr) {
                System.out.print(ele + "\t");
            }
            System.out.println();
        }
    }
}
