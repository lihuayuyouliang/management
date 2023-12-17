package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BasicInfoMapper;
import com.example.demo.dao.StdFieldQuoteMapper;
import com.example.demo.entity.BasicInfo;
import com.example.demo.entity.StdFieldQuote;
import com.example.demo.service.IBasicInfoService;
import com.example.demo.service.IStdFieldQuoteService;
import org.springframework.stereotype.Service;

@Service
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements IBasicInfoService {
}
