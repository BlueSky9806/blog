package com.zhanghao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhanghao.po.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<com.zhanghao.po.Blog> {

    List<Blog> listBlogs();

    List<Blog> listBlogsByTitle(String searchContent);

    List<Blog> listBlogsByCriteria(Blog blog);

    int saveBlog(Blog blog);

    List<Blog> listLatestBlogs();

    Blog selectBlogById(Integer id);

    List<Blog> listBlogsByTypeId(Integer id);

    /*
    List<Blog> pageByTypeId(Integer typeId);*/
}
