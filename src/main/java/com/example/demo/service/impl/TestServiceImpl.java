package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.service.TestService;
import com.example.demo.utils.ResultVOUtil;
import com.example.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zty200329
 * @date 2020/7/15 9:32
 * @describe:
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResultVO insert(RegisterForm registerForm) {
        User user = new User();
        BeanUtils.copyProperties(registerForm,user);
        int i = userMapper.insert(user);
        log.info("插入的数据量："+ i +"条");
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO delByUserName(String username) {
        userMapper.deleteByUserName(username);
        return ResultVOUtil.success();
    }
}
