package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName(value = "platform_relation")
public class Relation {
    @Id    //设置主键为id
    private int id;
    private String callerName;
    private String calleeName;
}
