package com.yh.spring.demo.test.service;

import com.yh.spring.demo.service.SalaryService;
import com.yh.spring.demo.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SalaryServiceTest extends BaseTest {

    @Autowired
    private SalaryService salaryService;

    @Test
    public void testUploadFile() {
        String filePath = "D:\\myhadooptext.txt";
        boolean b = salaryService.uploadFile(filePath);
        Assert.assertTrue(b);
    }
}
