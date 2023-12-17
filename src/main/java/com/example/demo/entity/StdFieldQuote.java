package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@TableName(value = "platform_std")
public class StdFieldQuote {
    @Id    //设置主键为id
    private int id;
    @Column(name="object_id")
    private String objectId;
    @Column(name="name")
    private String name;
    @Column(name="flag")
    private String flag;
    @Column(name="comment")
    private String comment;
    @Column(name = "import_or")
    private int importOr;
}
