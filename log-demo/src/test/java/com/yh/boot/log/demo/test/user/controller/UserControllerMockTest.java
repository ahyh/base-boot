package com.yh.boot.log.demo.test.user.controller;

import com.yh.boot.log.demo.controller.UserController;
import com.yh.boot.log.demo.dao.UserDao;
import com.yh.boot.log.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {UserController.class})
@AutoConfigureMybatis
@MapperScan(value = "com.yh.boot.log.demo.dao", basePackageClasses = UserDao.class)
@ComponentScan(value = "com.yh.boot.log.demo.service", basePackageClasses = UserService.class)
public class UserControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String resultStr = response.getContentAsString();
        System.out.println(resultStr);
    }
}
