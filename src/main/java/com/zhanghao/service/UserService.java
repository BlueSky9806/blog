package com.zhanghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhanghao.po.User;

public interface UserService extends IService<User> {

    // 根据用户名和密码获取一个用户
    User getOne(String username, String password);
}
