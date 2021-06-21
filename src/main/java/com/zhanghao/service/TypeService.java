package com.zhanghao.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhanghao.po.Type;
import com.zhanghao.vo.TypesVO;

import java.util.List;

public interface TypeService extends IService<Type> {

    // 查询所有分类带查询
    Page<Type> listTypes(Integer pn, Integer size);

    // 保存分类
    boolean saveType(Type type);

    // 获取一个分类
    Type getType(Type type);

    // 通过分类id获取分类
    Type getTypeById(Integer id);

    // 修改分类
    boolean updateType(Type type);

    // 删除分类
    void deleteTypeById(Integer id);

    // 获取所有分类
    List<Type> listTypes();

    // 此方法与listTypes方法效果一样只是集合类型不一样
    List<TypesVO> getAllTypes();
}
