package com.yh.boot.fastdfs.demo.service;

/**
 * FastDfs接口，定义文件的上传、下载以及删除方法
 *
 * @author huanyan2
 */
public interface FastDfsService {

    /**
     * 上传文件
     *
     * @param bytes    文件字节数组
     * @param fileType 文件类型
     * @return 文件的url
     */
    String upload(byte[] bytes, String fileType);

    /**
     * 下载文件
     *
     * @param path 文件的路径
     * @return 文件字节数组
     */
    byte[] downFileBytes(String path);

    /**
     * 删除文件
     *
     * @param path 文件路径
     * @return 是否成功删除文件
     */
    boolean deleteFile(String path);
}
