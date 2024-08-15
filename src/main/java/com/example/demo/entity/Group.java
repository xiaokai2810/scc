package com.example.demo.entity;

public class Group {
    private Integer id;
    private String gid;
    private String lines;


    public Group(Integer id, String gid, String lines) {
        this.id = id;
        this.gid = gid;
        this.lines=lines;

    }

    public Integer getId() {
        return id;
    }

    public String getGid() {
        return gid;
    }



    public String getLines() {
        return lines;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gid='" + gid + '\'' +
                ", lines='" + lines + '\'' +
                '}';
    }
}