package com.yh.spring.demo.common.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件相关工具类
 *
 * @author yanhuan
 */
public class FileUtil {

    public static File getFileByMultipartFile(MultipartFile multipartFile) throws Exception {
        File file = new File("/aaa." + FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        return file;
    }

}
