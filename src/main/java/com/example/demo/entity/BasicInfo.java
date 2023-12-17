package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName(value = "platform_basic")
public class BasicInfo {
    @Id    //设置主键为id
    private int id;
    private String objectId;
    private String version;
    private String updateDate;
    private String description;
    private String englishName;
    private String flag;
    private String resultSet;
    private String checkLicence;
    private String dataBase;
    private String functionId;
    private String serviceNo;
    private String requirementNo;
    private String requirementType;
    private String requirementLevel;
    private String auditNeed;
    private String auditType;
    private String auditLevel;
    private String operator;
    private String systemId;
    private String timeout;
    private String transMonitor;
    private String noCall;
    private String defaultCall;
    private String microService;
}
