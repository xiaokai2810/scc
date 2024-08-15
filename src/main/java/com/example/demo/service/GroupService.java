package com.example.demo.service;

import com.example.demo.entity.Group;

public interface GroupService {

    public Group Sel(Group group);

    public String Add(Group  group);

    public String Update(Group group);
}
