package com.example.demo.service;

import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.vo.ResultVO;

/**
 * @author zty200329
 * @date 2020/7/15 9:29
 * @describe:
 */
public interface TestService {
    /**
     * 插入用户
     * @param
     * @return
     */
    ResultVO insert(RegisterForm registerForm);

    ResultVO delByUserName(String username);
}
