package com.example.demo.mapper;

import com.example.demo.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {


    Group Sel(@Param("group")Group group);
    int Add(@Param("group")Group group);

}