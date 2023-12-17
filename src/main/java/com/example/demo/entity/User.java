package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

//@Entity   //标注为实体类
//@Table(name = "user")    //映射数据库user表
@Data
@ToString
@TableName(value = "user")
public class User {
//    @Id    //设置主键为id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private String phone;
//    @Column(name = "create_time")    //数据库采用驼峰式的结构，添加注释与该实体类createTime字段进行映射
//    private String createTime;
}
