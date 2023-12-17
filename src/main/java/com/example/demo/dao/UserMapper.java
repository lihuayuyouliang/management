package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper // 为了把mapper这个DAO交給Spring管理；为了不再写mapper映射文件；为一个添加@Mapper注解的接口自动生成一个实现类
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from  user")
//    List<User> findall();
//
//    @Insert("INSERT INTO user(name,age,sex,address,phone) VALUES (#{name}, #{age}, #{sex}, #{address}, #{phone})")
//    int insert(User user);
//
//    // 写了动态sql，那么这里的注解要删掉
//    // @Update("update user set name=#{name},age=#{age},sex=#{sex},address=#{address},phone=#{phone} where id=#{id}")
//    int update(User user);
//
//    // 对于多个参数来说，每个参数之前都要加上@Param注解，要不然会找不到对应的参数进而报错
//    @Delete("delete from user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from user limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize);
//
//    @Select("select count(*) from user")
//    Integer selectTotal();
//
    @Select("select * from user where name=#{name}")
    User searchByName(String name);
}
