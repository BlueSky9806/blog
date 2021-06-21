package com.zhanghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhanghao.mapper.TypeMapper;
import com.zhanghao.po.Type;
import com.zhanghao.service.TypeService;
import com.zhanghao.vo.TypesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    // Mybatis-plus分页查询
    @Override
    public Page<Type> listTypes(Integer pn, Integer size) {
        Page<Type> page = new Page<>(pn, size);
        return this.page(page);
    }

    @Override
    public Type getType(Type type) {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("name", type.getName());
        return this.getOne(wrapper);
    }

    @Override
    public Type getTypeById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean saveType(Type type) {
        return this.save(type);
    }

    @Override
    public boolean updateType(Type type) {
        return this.updateById(type);
    }

    @Override
    public void deleteTypeById(Integer id) {
        this.removeById(id);
    }

    @Override
    public List<Type> listTypes() {
        return typeMapper.listTypes();
    }

    @Override
    public List<TypesVO> getAllTypes() {
        return typeMapper.getAllTypes();
    }
}
