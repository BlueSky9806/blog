package com.zhanghao.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghao.po.Blog;
import com.zhanghao.po.Type;
import com.zhanghao.service.BlogService;
import com.zhanghao.service.TypeService;
import com.zhanghao.vo.TypesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/latestBlogs")
    public String latestBlogs(Model model) {
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("latestBlogs", latestBlogs);
        return "/_fragments :: latestBlogs";
    }

    // 首页加载分类、博客、最新博客信息
    @GetMapping("/")
    public String toIndex(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                          @RequestParam(value = "size", defaultValue = "4")Integer size,
                          Model model) {
        // 查询所有分类信息
        List<TypesVO> types = typeService.getAllTypes();
        // 博客分页查询
        PageInfo<Blog> pages = blogService.listBlogs(pn, size);
        // 底部最新发布博客三篇
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("types", types);
        model.addAttribute("blogs", pages);
        model.addAttribute("latestBlogs", latestBlogs);
        return "index";
    }

    // Ajax查询blog局部刷新
    @PostMapping("/")
    public String page(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                       @RequestParam(value = "size", defaultValue = "4")Integer size,
                       @RequestParam(value = "searchContent", defaultValue = "") String searchContent,
                       Model model) {
        if (null != searchContent && searchContent.length() > 0) {
            PageInfo<Blog> pages = blogService.listBlogsByTitle(pn, size, searchContent);
            model.addAttribute("blogs", pages);
        } else {
            PageInfo<Blog> pages = blogService.listBlogs(pn, size);
            model.addAttribute("blogs", pages);
        }
        return "index :: blogPage";
    }

    // 跳转博客详情页
    @GetMapping("/blogs/{id}")
    public String blogs(@PathVariable Integer id,
                        Model model) {
        Blog blog = blogService.getBlogAndConvert(id);
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("latestBlogs", latestBlogs);
        model.addAttribute("blog", blog);
        return "/blog";
    }

    @GetMapping("/login")
    public String toBlog() {
        return "admin/login";
    }
}
