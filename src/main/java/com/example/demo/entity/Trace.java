package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@TableName(value = "platform")
public class Trace {
    @Id    //设置主键为id
    private int id;
    private String objectId;
    private String name;
    private String extend;
    private String code;
    private String filePath;
    private String version;
}


