package com.zhanghao.controller;

import com.github.pagehelper.PageInfo;
import com.zhanghao.po.Blog;
import com.zhanghao.po.Type;
import com.zhanghao.service.BlogService;
import com.zhanghao.service.TypeService;
import com.zhanghao.vo.TypesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexTypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    // 跳转分类页面（查询分类信息和博客信息）
    @GetMapping("types/{id}")
    public String types(@PathVariable("id")Integer id,
                        @RequestParam(value = "pn", defaultValue = "1")Integer pn,
                        @RequestParam(value = "size", defaultValue = "3")Integer size,
                        Model model) {
        List<TypesVO> types = typeService.getAllTypes();
        if (id == -1) {
            id = types.get(0).getId();
        }
        PageInfo<Blog> blogs = blogService.listBlogsByTypeId(pn, size, id);
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("latestBlogs", latestBlogs);
        model.addAttribute("blogs", blogs);
        model.addAttribute("activeTypeId", id);
        model.addAttribute("types", types);
        return "types";
    }

    // Ajax请求进行分页
    @PostMapping("types")
    public String pageTypes(@RequestParam("pn")Integer pn,
                            @RequestParam(value = "size", defaultValue = "3")Integer size,
                            @RequestParam("type_id")Integer type_id,
                            Model model) {
        PageInfo<Blog> blogs = blogService.listBlogsByTypeId(pn, size, type_id);
        model.addAttribute("blogs", blogs);
        return "types :: typePage";
    }
}
