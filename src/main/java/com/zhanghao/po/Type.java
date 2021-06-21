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

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "tb_type")
@Accessors(chain = true)
public class Type {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    @TableField(exist = false)
    private ArrayList<Blog> blogs = new ArrayList<>();
}
