package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.StdFieldQuoteMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.StdFieldQuote;
import com.example.demo.entity.User;
import com.example.demo.service.IStdFieldQuoteService;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class StdFieldQuoteServiceImpl extends ServiceImpl<StdFieldQuoteMapper, StdFieldQuote> implements IStdFieldQuoteService {
}
