package com.example.demo.entity;

import java.security.Timestamp;

public class Group {
    private Integer id;
    private String gid;
    private String lines;
    private Timestamp updatedAt;  // 新增时间字段
    public Group(Integer id,String gid,String lines)
    {
        this.id=id;
        this.gid=gid;
        this.lines=lines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }
}