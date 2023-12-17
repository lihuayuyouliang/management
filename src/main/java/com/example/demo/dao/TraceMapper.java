package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.Trace;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TraceMapper extends BaseMapper<Trace> {
    @Select("select * from platform where object_id=#{objectId}")
    Trace searchByObjectId(String objectId);
}
