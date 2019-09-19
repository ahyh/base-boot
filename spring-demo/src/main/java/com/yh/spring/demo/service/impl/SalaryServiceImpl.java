package com.yh.spring.demo.service.impl;

import com.yh.spring.demo.service.SalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * SalaryServiceImpl
 *
 * @author yanhuan
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    private static final Logger logger = LoggerFactory.getLogger(SalaryServiceImpl.class);

    private static final String URL = "http://localhost:8098/spring-demo/parse/file";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean uploadFile(String filePath) {
        try {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("multipart/form-data");
            // 设置请求的格式类型
            headers.setContentType(type);
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("file", fileSystemResource);
            form.add("username", "yanhuan");
            form.add("password", "123456");
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
            ResponseEntity<String> responseResponseEntity = restTemplate.postForEntity(URL, files, String.class);
            String body = responseResponseEntity.getBody();
            System.out.println(body);
            return true;
        } catch (Exception e) {
            logger.error("SalaryServiceImpl uploadFile error:{}", e);
            return false;
        }
    }
}
