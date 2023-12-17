package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.StdFieldQuote;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StdFieldQuoteMapper extends BaseMapper<StdFieldQuote> {
//    @Insert("INSERT INTO platform_std(id,object_id,name,flag,comment,import_or) VALUES (#{id}, #{object_id},#{name}, #{flag}, #{comment},#{import_or})")
//    void insert(StdFieldQuote stdFieldQuote);
}
