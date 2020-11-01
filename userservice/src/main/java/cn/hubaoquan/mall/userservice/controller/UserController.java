package cn.hubaoquan.mall.userservice.controller;

import cn.hubaoquan.mall.userservice.mapper.UserMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapper;

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable Integer id) {
        return userMapper.selectByPrimaryKey(id).toString();
    }

    
}
