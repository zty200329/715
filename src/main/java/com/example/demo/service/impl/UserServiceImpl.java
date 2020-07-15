package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.enums.ResultEnum;
import com.example.demo.form.LoginForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.model.User;
import com.example.demo.security.JwtProperties;
import com.example.demo.security.JwtUserDetailServiceImpl;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.ResultVOUtil;
import com.example.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zty200329
 * @date 2020/7/15 10:56
 * @describe:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public User getUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String key = "anonymousUser";
        if (!userName.equals(key)) {
            return getUserByUsername(userName);
        }
        return null;
    }

    @Override
    public ResultVO UserRegister(UserRegisterForm userRegisterForm) {
        boolean isHave = (userMapper.selectUserByUsername(userRegisterForm.getUserName()) != null);
        if (isHave){
            return ResultVOUtil.error(ResultEnum.USER_HAS_EXIST);
        }
        log.error(userRegisterForm.toString());
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm,user);
        user.setPasswd(passwordEncoder.encode(userRegisterForm.getPasswd()));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.selectUserByUsername(loginForm.getUsername());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getUsername());
        if (!(new BCryptPasswordEncoder().matches(loginForm.getPasswd(), userDetails.getPassword()))) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPasswd(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);
        Map<String, Serializable> map = new HashMap<>();
        map.put("name",user.getUserName());
        map.put("role", user.getRole());
        map.put("token", realToken);
        return ResultVOUtil.success(map);
    }
}
