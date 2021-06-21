package com.zhanghao.controller.admin;

import com.zhanghao.po.Blog;
import com.zhanghao.po.User;
import com.zhanghao.service.BlogService;
import com.zhanghao.service.UserService;
import com.zhanghao.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping()
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        HttpSession session,
                        RedirectAttributes attributes
                        ) {
        User user = userService.getOne(username, password);
        if (null != user) {
            LoginVO loginVO = new LoginVO();
            BeanUtils.copyProperties(user, loginVO);
            session.setAttribute("user", loginVO);
            return "redirect:/admin/index.html";
        }
        attributes.addFlashAttribute("message", "用户名或密码错误");
        return "redirect:/admin";
    }

    @GetMapping("/index.html")
    public String toIndex(Model model) {
        List<Blog> latestBlogs = blogService.listLatestBlogs();
        model.addAttribute("latestBlogs", latestBlogs);
        return "/admin/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
