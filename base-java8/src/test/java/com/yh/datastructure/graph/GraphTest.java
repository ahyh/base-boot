package com.yh.datastructure.graph;

import com.yh.java8.datastructure.graph.Graph;
import org.junit.Test;

public class GraphTest {

    private static Graph<String> graph;

    static{
        graph = new Graph<>(5);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
    }

    @Test
    public void testPrint(){
        graph.printGraph();
    }

    @Test
    public void testDFS(){
        graph.dfs();
    }

}
