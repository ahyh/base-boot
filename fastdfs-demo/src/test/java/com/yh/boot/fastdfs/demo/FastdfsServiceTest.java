package com.yh.boot.fastdfs.demo;

import com.yh.boot.fastdfs.demo.service.FastDfsService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class FastdfsServiceTest extends FastdfsDemoApplicationTests {

    @Autowired
    private FastDfsService fastDfsService;

    @Test
    public void testUpload() throws Exception {
        byte[] bytes = FileUtils.readFileToByteArray(new File("F:\\test\\000.jpg"));
        String filePath = fastDfsService.upload(bytes, "jpg");
        System.out.println(filePath);
        Assert.assertTrue(StringUtils.isNotBlank(filePath));
    }

    @Test
    public void testDown() throws Exception {
        byte[] bytes = fastDfsService.downFileBytes("group1/M00/00/2D/rB8uTF0Z8GyEA8phAAAAAFUx4TU128.jpg");
        FileUtils.writeByteArrayToFile(new File("F:\\test\\0000000.jpg"),bytes);
        System.out.println();
    }

    @Test
    public void testDelete() throws Exception {

    }
}
