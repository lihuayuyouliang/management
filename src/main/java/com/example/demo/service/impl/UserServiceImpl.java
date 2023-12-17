package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
//    @Resource
//    private UserMapper userMapper;

//    public int save(User user){
//        if (user.getId()==null){
//            return userMapper.insert(user);
//        }else {
//            return userMapper.update(user);
//        }
//    }

//    @Override
//    public List<User> list() {
//        return userMapper.findall();
//    }
    public boolean saveUser(User user){
        return saveOrUpdate(user);
    }
}
