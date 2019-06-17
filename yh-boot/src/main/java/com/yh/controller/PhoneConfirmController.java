package com.yh.controller;

import com.yh.dto.Result;
import com.yh.service.PhoneConfirmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis实现的手机验证码相关功能
 *
 * @author yanhuan
 */
@Api("手机验证码相关功能")
@RestController(value = "/phone/confirm")
@ConditionalOnProperty(name = "enable-redis-flag", havingValue = "true")
public class PhoneConfirmController {

    private static final Logger logger = LoggerFactory.getLogger(PhoneConfirmController.class);

    @Autowired
    private PhoneConfirmService phoneConfirmService;

    @ApiOperation(value = "根据phone获取验证码")
    @GetMapping(value = "/code/{phone}")
    public Result<String> getConfirmCode(@PathVariable("phone") String phone) {
        String msg;
        try {
            String confirmCode = phoneConfirmService.getConfirmCode(phone);
            return new Result<>(1, confirmCode, "成功");
        } catch (Exception e) {
            logger.error("PhoneConfirmController getConfirmCode phone:{},error:{}", phone, e);
            msg = e.getMessage();
        }
        return new Result<>(0, null, msg);
    }

    @ApiOperation(value = "根据phone获取已验证次数")
    @GetMapping(value = "/count/{phone}")
    public Result<Integer> getConfirmCount(@PathVariable("phone") String phone) {
        String msg;
        try {
            Integer confirmCount = phoneConfirmService.confirmCount(phone);
            return new Result<>(1, confirmCount, "成功");
        } catch (Exception e) {
            logger.error("PhoneConfirmController getConfirmCode phone:{},error:{}", phone, e);
            msg = e.getMessage();
        }
        return new Result<>(0, null, msg);
    }

    @ApiOperation(value = "验证")
    @GetMapping(value = "/compare/{phone}/{code}")
    public Result<Boolean> getConfirmCount(@PathVariable("phone") String phone,
                                           @PathVariable("code") String code) {
        String msg;
        try {
            Boolean confirm = phoneConfirmService.confirm(phone, code);
            return new Result<>(1, confirm, "成功");
        } catch (Exception e) {
            logger.error("PhoneConfirmController getConfirmCode phone:{},error:{}", phone, e);
            msg = e.getMessage();
        }
        return new Result<>(0, null, msg);
    }


}
