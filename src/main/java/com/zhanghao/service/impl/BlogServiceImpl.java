package com.zhanghao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhanghao.exception.NotFoundException;
import com.zhanghao.mapper.BlogMapper;
import com.zhanghao.po.Blog;
import com.zhanghao.service.BlogService;
import com.zhanghao.service.TypeService;
import com.zhanghao.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, com.zhanghao.po.Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TypeService typeService;

    // PageHelper进行分页查询
    @Override
    public PageInfo<Blog> listBlogs(Integer pn, Integer size) {
        PageHelper.startPage(pn, size);
        List<Blog> blogs = blogMapper.listBlogs();
        return new PageInfo<>(blogs);
    }

    // PageHelper进行分页查询
    @Override
    public PageInfo<Blog> listBlogsByTitle(Integer pn, Integer size, String searchContent) {
        PageHelper.startPage(pn, size);
        List<Blog> blogs = blogMapper.listBlogsByTitle(searchContent);
        return new PageInfo<>(blogs);
    }

    // pageHelper分页
    @Override
    public PageInfo<Blog> listBlogsByCriteria(Integer pn, Integer size, Blog blog) {
        PageHelper.startPage(pn, size);
        List<Blog> blogs = blogMapper.listBlogsByCriteria(blog);
        return new PageInfo<>(blogs);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        // 初始化浏览次数和时间
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        return blogMapper.saveBlog(blog);
    }

    @Transactional
    @Override
    public boolean deleteBlog(Integer id) {
        return this.removeById(id);
    }

    @Override
    public List<Blog> listLatestBlogs() {
        return blogMapper.listLatestBlogs();
    }

    // 通过id获取所有博客信息后，将博客内容转化为HTML格式
    @Override
    public Blog getBlogAndConvert(Integer id) {
        Blog blog = blogMapper.selectBlogById(id);
        if (null == blog) {
            throw new NotFoundException("该博客已被删除");
        }
        Blog po = new Blog();
        BeanUtils.copyProperties(blog, po);
        String content = po.getContent();
        po.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return po;
    }

    // pageHelper分页查询
    @Override
    public PageInfo<Blog> listBlogsByTypeId(Integer pn, Integer size, Integer id) {
        PageHelper.startPage(pn, size);
        List<Blog> blogs = blogMapper.listBlogsByTypeId(id);
        return new PageInfo<>(blogs);
    }

    /*@Override
    @Override
    public Boolean isExist(Integer id) {
        QueryWrapper<com.zhanghao.pojo1.Blog> wrapper = new QueryWrapper<>();
        wrapper.select("id").eq("id", id);
        return blogMapper.selectOne(wrapper) == null;
    }

    private List<BlogsVO> po2vo(List<Blog> list) {
       List<BlogsVO> blogsVOS = new ArrayList<>();
        for (Blog blog : list) {
            BlogsVO blogsVO = new BlogsVO();
            blogsVO.setTitle(blog.getTitle());
            blogsVO.setType(blog.getType());
            blogsVO.setRecommend(blog.isRecommend());
            blogsVO.setUpdateTime(blog.getUpdateTime());
            blogsVOS.add(blogsVO);
        }
        return blogsVOS;
    }*/
}
