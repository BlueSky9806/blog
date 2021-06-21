package com.zhanghao.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zhanghao.po.Blog;
import com.zhanghao.po.Type;
import com.zhanghao.po.User;
import com.zhanghao.service.BlogService;
import com.zhanghao.service.TypeService;
import com.zhanghao.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;


    @GetMapping("/blogs")
    public String toBlogs(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                          @RequestParam(value = "size", defaultValue = "3")Integer size,
                          Model model) {
        // 查分类
        List<Type> types = typeService.listTypes();
        // 查询博客信息联合分类表
        PageInfo<Blog> blogPageInfo = blogService.listBlogs(pn, size);
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("latestBlogs", latestBlogs);
        model.addAttribute("types", types);
        model.addAttribute("blogPages", blogPageInfo);
        return "admin/blogs";
    }

    // 条件查询（title、type.id、recommend、pn）
    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                         @RequestParam(value = "size", defaultValue = "3")Integer size,
                         Blog blog,
                         Model model) {
        // blog分类
        PageInfo<Blog> blogPageInfo = blogService.listBlogsByCriteria(pn, size, blog);
        model.addAttribute("blogPages", blogPageInfo);
        return "admin/blogs :: blogList";
    }

    // 跳转到发布博客页面同时查询分类信息
    @GetMapping("/publishBlog")
    public String input(Model model) {
        List<Type> types = typeService.listTypes();
        model.addAttribute("types", types);
        return "admin/blogs-publish";
    }

    // 保存博客
    @PostMapping("/blogs")
    public String addBlog(Blog blog,
                          HttpSession session,
                          RedirectAttributes attributes) {
        // 先获取用户
        LoginVO user = (LoginVO) session.getAttribute("user");
        User copyUser = new User();
        BeanUtils.copyProperties(user, copyUser);
        blog.setUser(copyUser);
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "发布成功");
        return "redirect:/admin/blogs";
    }

    // 删除博客根据id
    @DeleteMapping("blogs/{id}")
    public String blogs(@PathVariable("id") Integer id,
                        Integer pn,
                        RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addAttribute("pn", pn);
        return "redirect:/admin/blogs";
    }
}
