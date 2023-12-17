package com.example.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Struct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 是@Controller和@ResponseBody的集合，前者标识该类代表控制器类，后者的作用其实是将java对象转为json格式的数据，返回给浏览器
@RequestMapping("/user") // 使用 @RequestMapping 来映射请求，也就是通过它来指定控制器可以处理哪些URL请求
public class UserController {
    @Resource // 依赖注入
    private UserMapper userMapper;

    @Resource
    private IUserService userService;
    //
    @GetMapping // 是@RequestMapping(method = RequestMethod.GET)缩写的组合注解，用于将 HTTP 的get 请求映射到特定处理程序的方法注解
    public List<User> findAll(){
        List<User>res= userService.list();
        System.out.println("hello");
        return res;
    }
    //
    @GetMapping("/search/{name}")
    public User search(@PathVariable String name){
        return userMapper.searchByName(name);
    }

    @PostMapping("/add") // 是@RequestMapping(method = RequestMethod.POST)缩写的组合注解，用于将 HTTP 的post 请求映射到特定处理程序的方法注解
    public boolean save(@RequestBody User user){
        // 新增或更新
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}") // 参考上面的注释
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

    // 没用上，vue没弄
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
//        pageNum=(pageNum-1)*pageSize;
//        List<User>data=userMapper.selectPage(pageNum,pageSize);
//        Integer total=userMapper.selectTotal();
//        Map<String, Object>res=new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("id", "id");
//        writer.addHeaderAlias("name", "姓名");
//        writer.addHeaderAlias("age", "年龄");
//        writer.addHeaderAlias("sex", "性别");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("phone", "手机号");
//        writer.addHeaderAlias("createTime", "创建时间");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @PostMapping("/import")
    public boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream=file.getInputStream();
        ExcelReader reader=ExcelUtil.getReader(inputStream);
        // 这里表头必须是英文，且要与javabean的属性对应
        List<User> list=reader.readAll(User.class);
//        System.out.println(list);
        userService.saveBatch(list);
        return true;
    }
}
