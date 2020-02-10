package com.yh.datastructure.huffman;

import com.yh.java8.datastructure.huffman.HuffmanCode;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class HuffmanCodeTest {

    @Test
    public void testHuffmanCode() {
        List<HuffmanCode.Node> nodes = HuffmanCode.getNodes("i like like like java, really like java java");
        nodes.stream().forEach(System.out::println);
        HuffmanCode.Node rootNode = HuffmanCode.createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodeMap = HuffmanCode.getHuffmanCodeMap(rootNode);
        System.out.println(huffmanCodeMap);
    }

    @Test
    public void testHuffmanZipAndUnzip() {
        String s = "i like like like java do you like a java";
        HuffmanCode.Node rootNode = HuffmanCode.createHuffmanTree(HuffmanCode.getNodes(s));
        Map<Byte, String> huffmanCodeMap = HuffmanCode.getHuffmanCodeMap(rootNode);

        byte[] zipBytes = HuffmanCode.zip(s.getBytes(), huffmanCodeMap);

        String unzip = HuffmanCode.unzip(zipBytes, huffmanCodeMap);

        System.out.println(unzip);
    }
}
