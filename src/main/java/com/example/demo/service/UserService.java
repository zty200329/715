package com.example.demo.service;

import com.example.demo.form.LoginForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.model.User;
import com.example.demo.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zty200329
 * @date 2020/7/15 10:51
 * @describe:
 */
@Service
public interface UserService {
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    User getUserByUsername(String userName);

    /**
     * 获取当前用户
     *
     * @return
     */
    User getCurrentUser();

    /**
     * 用户注册
     * @param userRegisterForm
     * @return
     */
    ResultVO UserRegister(UserRegisterForm userRegisterForm);

    /**
     * 登录
     * @param loginForm
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);
}
