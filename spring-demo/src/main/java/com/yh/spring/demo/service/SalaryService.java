package com.yh.spring.demo.service;

/**
 * SalaryService
 *
 * @author yanhuan
 */
public interface SalaryService {

    /**
     * 使用restTemplate发送文件到服务端
     *
     * @param filePath 文件路径
     * @return 是否成功处理
     */
    boolean uploadFile(String filePath);
}
