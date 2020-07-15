package com.example.demo.controller;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.service.TestService;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zty200329
 * @date 2020/7/15 9:38
 * @describe:
 */
@RestController
@Slf4j
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * 我不写form表单了哈
     * @param
     * @return
     */
    @PostMapping("/insertUser")
    @ApiOperation("注册用户")
    public ResultVO insertUser(RegisterForm registerForm){
        return testService.insert(registerForm);
    }

    @PostMapping("/delByUserName")
    @ApiOperation("根据用户名删除")
    public ResultVO delByUserName(String username){
        return testService.delByUserName(username);
    }

}
