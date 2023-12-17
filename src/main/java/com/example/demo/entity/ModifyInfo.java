package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName(value = "platform_modify")
public class ModifyInfo {
    @Id    //设置主键为id
    private int id;
    private String objectId;
    private String position;
    private String date;
    private String version;
    private String serialNumber;
    private String user;
    private String principal;
    private String cause;
    private String content;
    private String tester;
}
