package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yn2333
 * @Date 2020/03/03
 */
@Service
public class GroupService {
    @Autowired
    GroupMapper groupMapper;
    public Group Sel(Group group) {
        return groupMapper.Sel(group);
    }
    public String Add( Group  group) {
        groupMapper.Sel(group);
        int a = groupMapper.Add(group);

        if (a == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }
//
//    public String Update(User user) {
//        int a = userMapper.Update(user);
//        if (a == 1) {
//            return "修改成功";
//        } else {
//            return "修改失败";
//        }
//    }
//
//    public String Delete(User user) {
//        int a = userMapper.Delete(user);
//        if (a == 1) {
//            return "删除成功";
//        } else {
//            return "删除失败";
//        }
//    }
//


}