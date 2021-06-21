package com.zhanghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhanghao.mapper.UserMapper;
import com.zhanghao.po.User;
import com.zhanghao.service.UserService;
import com.zhanghao.util.MD5Utils;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getOne(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "nick_name", "username", "avatar")
                .eq("username", username)
                .eq("password", MD5Utils.encode(password));
        return this.getOne(wrapper);
    }
}
