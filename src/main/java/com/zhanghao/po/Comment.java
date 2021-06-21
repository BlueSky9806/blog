package com.zhanghao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName(value = "tb_comment")
public class Comment {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String nickName;

    private String email;

    private String content;

    private String avatar;

    private Date createTime;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;
}
