package com.zhanghao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName(value = "tb_blog")
public class Blog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    @TableField(value = "firstPicture")
    private String firstPicture;

    private String flag;

    private Integer views;

    private boolean appreciation;

    @TableField(value = "shareStatement")
    private boolean shareStatement;

    private boolean comment;

    private boolean published;

    private boolean recommend;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private ArrayList<Comment> comments = new ArrayList<>();
}
