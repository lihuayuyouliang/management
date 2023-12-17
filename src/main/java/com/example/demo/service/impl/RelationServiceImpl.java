package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BasicInfoMapper;
import com.example.demo.dao.RelationMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.Relation;
import com.example.demo.service.IBasicInfoService;
import com.example.demo.service.IRelationService;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {
}
