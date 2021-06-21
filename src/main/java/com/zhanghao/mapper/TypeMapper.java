package com.zhanghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanghao.po.Type;
import com.zhanghao.vo.TypesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    // 查询所有分类
    List<Type> listTypes();

    List<TypesVO> getAllTypes();
}
