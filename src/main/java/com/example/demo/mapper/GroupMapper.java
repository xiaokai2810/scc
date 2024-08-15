package com.example.demo.mapper;

import com.example.demo.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface GroupMapper {


    Group findByGid(String gid);
    Group Sel(@Param("group")Group group);
    int Add(@Param("group")Group group);

    int Update(@Param("group")Group group);
}