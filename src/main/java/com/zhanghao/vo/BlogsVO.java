package com.zhanghao.vo;

import com.zhanghao.po.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogsVO {

    private Long id;

    private String title;

    private Type type;

    private Boolean recommend;

    private Date updateTime;

    private int count;
}
