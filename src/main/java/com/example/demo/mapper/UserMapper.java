package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(@Param("user")User user);

    int Add(@Param("user")User user);

    int Update(@Param("user")User user);

    int Delete(@Param("user")User user);
}