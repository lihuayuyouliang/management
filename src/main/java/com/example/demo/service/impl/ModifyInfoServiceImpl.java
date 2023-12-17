package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BasicInfoMapper;
import com.example.demo.dao.ModifyInfoMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.ModifyInfo;
import com.example.demo.service.IBasicInfoService;
import com.example.demo.service.IModifyInfoService;
import org.springframework.stereotype.Service;

@Service
public class ModifyInfoServiceImpl extends ServiceImpl<ModifyInfoMapper, ModifyInfo> implements IModifyInfoService {
}
