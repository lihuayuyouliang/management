package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Trace;
import com.example.demo.entity.User;

import java.io.IOException;

public interface ITraceService extends IService<Trace> {
    void searchFile() throws IOException;
    void saveOrUpdateBasedOnObjectId(Trace trace);
    boolean saveUser(Trace trace);
}
