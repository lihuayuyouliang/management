package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;

@Data
@TableName(value = "platform_variable")
public class VariableField {
    @Id    //设置主键为id
    private int id;
    private String objectId;
    private String name;
    private String type;
    private String dv;
    private String paramName;
    // 这个字段不能用和xml里一样的desc，因为和mysql数据库关键字一样，会插入数据库报错
    private String description;
}
