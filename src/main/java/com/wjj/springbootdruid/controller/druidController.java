package com.wjj.springbootdruid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@RequestMapping("/druid")
@RestController
public class druidController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public List<Map<String, Object>> userList(){
        String sql = "select * from user ";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    // 等价于 RequestMapping 区别在于GetMapping只能映射一次
    @GetMapping("/add")
    public String addUser(){
        System.out.println("hello");
        String sql = "insert into user (name, password) values ('狂神说','101')";
        jdbcTemplate.update(sql);
        return "addOk";
    }

    @GetMapping("/update/{id}")
    public  String updataUser(@PathVariable("id") int id){
        String sql = "update user set name = ? ,password = ? where id = " + id;
        //数据
        Object[] objects = new Object[2];
        objects[0] = "秦疆";
        objects[1] = "2473674";
        jdbcTemplate.update(sql,objects);
        //查询
        return "updateOk";

    }

    // 在url路径中传入参数要删除的用户的参数
    @GetMapping("/delete/{id}")
    public String delUser(@PathVariable("id") int id){
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql,id);
        return "deleteok";
    }

}
