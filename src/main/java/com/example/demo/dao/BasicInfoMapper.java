package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.StdFieldQuote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {
}
