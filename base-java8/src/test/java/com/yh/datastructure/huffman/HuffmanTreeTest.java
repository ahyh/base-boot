package com.yh.datastructure.huffman;

import com.yh.java8.datastructure.huffman.HuffmanTree;
import org.junit.Test;

public class HuffmanTreeTest {

    @Test
    public void testHuffmanTree() {
        int[] nums = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree(nums);
        huffmanTree.printByPreOrder();
    }
}
