package com.zhanghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanghao.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
