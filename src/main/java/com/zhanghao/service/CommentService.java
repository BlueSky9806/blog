package com.zhanghao.service;

import com.zhanghao.po.Comment;

import java.util.List;

public interface CommentService {

    // 查询所有评论根据博客id
    List<Comment> listCommentByBlogId(Integer blogId);

    // 保存评论信息
    Integer saveComment(Comment comment);
}
