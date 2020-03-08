package com.yh.datastructure;

import com.yh.java8.algorithm.KurskalCase;
import org.junit.Test;

public class KurskalTest {

    private static int INF = Integer.MAX_VALUE;

    private static char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    private static int[][] matrix = {
            {0, 12, INF, INF, INF, 16, 14},
            {12, 0, 10, INF, INF, 7, INF},
            {INF, 10, 0, 3, 5, 6, INF},
            {INF, INF, 3, 0, 4, INF, INF},
            {INF, INF, 5, 4, 0, 2, 8},
            {16, 7, 6, INF, 2, 0, 9},
            {14, INF, INF, INF, 8, 9, 0}
    };

    @Test
    public void test() {
        KurskalCase kurskalCase = new KurskalCase(vertex, matrix);
        kurskalCase.print();
    }
}
