package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int deleteByUserName(String userName);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectUserByUsername(String name);
}