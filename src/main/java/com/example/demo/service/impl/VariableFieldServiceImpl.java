package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BasicInfoMapper;
import com.example.demo.dao.VariableFieldMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.VariableField;
import com.example.demo.service.IBasicInfoService;
import com.example.demo.service.IVariableFieldService;
import org.springframework.stereotype.Service;

@Service
public class VariableFieldServiceImpl extends ServiceImpl<VariableFieldMapper, VariableField> implements IVariableFieldService {
}
