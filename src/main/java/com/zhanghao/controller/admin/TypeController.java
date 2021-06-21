package com.zhanghao.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhanghao.po.Type;
import com.zhanghao.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 查询分类数据及带分页
     * @param pn 页码，默认第一页；分页查询时接收值。
     * @param size 每页大小，默认显示3条数据
     * @param model 携带数据
     * @return "admin/types"
     */
    @GetMapping("/types")
    public String types(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                        @RequestParam(value = "size", defaultValue = "3") Integer size,
                        Model model) {
        Page<Type> typePage = typeService.listTypes(pn, size);
        model.addAttribute("typePage", typePage);
        return "/admin/types";
    }

    // 处理页面跳转
    @GetMapping("/editType")
    public String typeInput() {
        return "admin/type-save";
    }

    // 保存分类
    @PostMapping("/editType")
    public String addType(Type type,
                          RedirectAttributes redirectAttributes) {
        // 判断该分类是否已存在
        if (null != typeService.getType(type)) {
            redirectAttributes.addFlashAttribute("message", type.getName() + "分类已存在");
            return "redirect:/admin/editType";
        }
        typeService.saveType(type);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/types";
    }

    // 修改分类时先查询该分类的信息再将信息回溯到修改页面
    @GetMapping("/editType/{id}")
    public String editType(@PathVariable("id") Integer id,
                           @RequestParam(value = "pn")Integer pn,
                           Model model) {
        Type type = typeService.getTypeById(id);
        if (null == type) {
            throw new RuntimeException("该博客不存在!");
        }
        model.addAttribute("typeName", type.getName());
        model.addAttribute("pn", pn);
        model.addAttribute("id", id);
        return "admin/type-save";
    }

    /**
     * 修改分类名称
     * @param id 分类id
     * @param pn 分类所在页码
     * @param type
     * @param redirectAttributes
     * @return "redirect:/admin/types"
     */
    @PostMapping("/editType/{id}")
    public String editType(@PathVariable("id")Integer id,
                           @RequestParam("pn")String pn,
                           Type type,
                           RedirectAttributes redirectAttributes) {
        // 先根据ID找到type
        Type existType = typeService.getTypeById(id);
        if (null == existType) {
            throw new RuntimeException("修改失败，该分类已删除！");
        }
        // 修改分类名称
        existType.setName(type.getName());
        typeService.updateType(existType);
        redirectAttributes.addAttribute("pn", pn);
        return "redirect:/admin/types";
    }

    // 删除页面
    @DeleteMapping("/editType/{id}")
    public String editType(@PathVariable("id")Integer id,
                           @RequestParam(name = "pn", defaultValue = "1")Integer pn,
                           RedirectAttributes redirectAttributes) {
        Type type = typeService.getTypeById(id);
        if (null == type) {
            throw new RuntimeException("删除失败，博客已删除！");
        }
        typeService.deleteTypeById(id);
        redirectAttributes.addAttribute("pn", pn);
        return "redirect:/admin/types";
    }
}