package com.yh.boot.log.demo.test.user.controller;

import com.yh.boot.log.demo.LogDemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * MOCK测试HTTP接口
 * 使用WebMvcTest注解标识这是测试MVC的类
 *
 * @author yanhuan
 */
@AutoConfigureMockMvc
public class UserControllerTest extends LogDemoApplicationTests{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getStatus());
        String resultStr = response.getContentAsString();
        System.out.println(resultStr);
    }

    @Test
    public void testAddUser(){

    }
}
