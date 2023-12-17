package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Relation;
import com.example.demo.entity.StdFieldQuote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationMapper extends BaseMapper<Relation> {
}
