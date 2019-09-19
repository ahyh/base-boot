package com.yh.spring.demo.controller;

import com.yh.spring.demo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Salary控制器
 *
 * @author yanhuan
 */
@Api
@RestController("/salary")
public class SalaryController {

    private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/parse/file", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result parseFile(@RequestParam String username,
                            @RequestParam String password,
                            @ApiParam(value = "薪资文件", required = true) @RequestParam("file") MultipartFile multipartFile) {
        Result result = new Result();
        try {
            System.out.println("username:" + username);
            System.out.println("password:" + password);
            System.out.println(multipartFile.getName());
            result.setData(true);
            result.setCode(1);
            result.setMessage("success");
        } catch (Exception e) {
            logger.error("SalaryController parseFile error:{}", e);
            result.setCode(-1);
            result.setData(false);
        }
        return result;
    }

}
