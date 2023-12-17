package com.example.demo.controller;

import com.example.demo.dao.TraceMapper;
import com.example.demo.entity.Trace;
import com.example.demo.entity.User;
import com.example.demo.service.ITraceService;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
public class TraceController {
    @Resource // 依赖注入
    private ITraceService traceService;
    @Resource
    private TraceMapper traceMapper;
    @PostMapping("/file")
    public void searchFile() throws IOException {
        // 新增或更新
        traceService.searchFile();
    }
    @GetMapping("/ok")
    public String index(){
        return "ok";
    }
    @GetMapping
    public List<Trace> findAll(){
        return traceService.list();
    }

    @PostMapping("/add")
    public boolean save(@RequestBody Trace trace){
        // 新增或更新
        return traceService.saveUser(trace);
    }

    @DeleteMapping("/{id}") // 参考上面的注释
    public boolean delete(@PathVariable Integer id){
        return traceService.removeById(id);
    }

    @GetMapping("/search/{objectId}")
    public Trace search(@PathVariable String objectId){
        return traceMapper.searchByObjectId(objectId);
    }
}
