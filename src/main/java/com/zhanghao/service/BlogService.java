package com.zhanghao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zhanghao.po.Blog;

import java.util.List;


public interface BlogService extends IService<com.zhanghao.po.Blog> {

    // 查询所有博客带分页
    PageInfo<Blog> listBlogs(Integer pn, Integer size);

    // 全局搜索
    PageInfo<Blog> listBlogsByTitle(Integer pn, Integer size, String searchContent);

    // 按条件分页查询
    PageInfo<Blog> listBlogsByCriteria(Integer pn, Integer size, Blog blog);

    // 保存博客
    int saveBlog(Blog blog);

    // 删除博客
    boolean deleteBlog(Integer id);

    // 获取最新博客
    List<Blog> listLatestBlogs();

    // 获取博客并且把博客内容转化HTML
    Blog getBlogAndConvert(Integer id);

    // 通过分类id查询所有博客并分页
    PageInfo<Blog> listBlogsByTypeId(Integer pn, Integer size, Integer id);
}
