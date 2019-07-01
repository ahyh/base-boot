package com.yh.boot.fastdfs.demo.service.impl;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.yh.boot.fastdfs.demo.service.FastDfsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * FastDfsService实现类，实现FastDfs文件上传和下载
 *
 * @author huanyan2
 */
@Component
public class FastDfsServiceImpl implements FastDfsService {

    private static final Logger logger = LoggerFactory.getLogger(FastDfsServiceImpl.class);

    @Autowired
    private AppendFileStorageClient storeClient;

    @Override
    public String upload(byte[] bytes, String fileType) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        if (StringUtils.isEmpty(fileType)) {
            fileType = "unKnown";
        }
        InputStream in = new ByteArrayInputStream(bytes);
        int inputLen = bytes.length;
        String inputType = fileType;
        StorePath storedPath = null;
        try {
            storedPath = storeClient.uploadAppenderFile(null, in, inputLen, inputType);
        } catch (Exception e) {
            logger.error("FastDfs upload error:{}", e);
            return "";
        }
        if (storedPath != null) {
            return storedPath.getFullPath();
        }
        return "";
    }

    @Override
    public byte[] downFileBytes(String path) {
        DownloadCallback<?> dc = new DownloadByteArray();
        String group = path.substring(0, path.indexOf('/'));
        String subPath = path.substring(path.indexOf('/') + 1);
        return (byte[]) storeClient.downloadFile(group, subPath, dc);
    }

    @Override
    public boolean deleteFile(String path) {

        try {
            storeClient.deleteFile(path.substring(0, path.indexOf("/")), path.substring(path.indexOf("/") + 1));
        } catch (Exception e) {
            logger.error("Fastdfs deleteFile error:{}", e);
            return false;
        }
        return true;
    }
}
