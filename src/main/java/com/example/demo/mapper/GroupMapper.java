package com.example.demo.mapper;

import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {

    Group Sel(@Param("group")Group group);

    int Add(@Param("group")Group group);

//    int Update(@Param("group")Group group);
//
//    int Delete(@Param("group")Group group);
}