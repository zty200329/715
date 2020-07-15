package com.example.demo.controller;

import com.example.demo.accessctro.RoleContro;
import com.example.demo.enums.RoleEnum;
import com.example.demo.form.LoginForm;
import com.example.demo.form.RegisterForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.model.User;
import com.example.demo.service.TestService;
import com.example.demo.service.UserService;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private UserService userService;

    /**
     * 我不写form表单了哈
     * @param
     * @return
     */
    @PostMapping("/insertUser")
    @ApiOperation("注册用户")
    public ResultVO insertUser(UserRegisterForm registerForm){
        return userService.UserRegister(registerForm);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultVO login(LoginForm loginForm, HttpServletResponse response){
        return userService.login(loginForm,response);
    }

    @PostMapping("/delete")
    @ApiOperation("删除")
    @RoleContro(role = RoleEnum.SUPPER_ADMIN)
    public ResultVO delete(String userName){
        return testService.delByUserName(userName);
    }
}
