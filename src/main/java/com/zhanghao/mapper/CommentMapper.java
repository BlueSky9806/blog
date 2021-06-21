package com.zhanghao.mapper;

import com.zhanghao.po.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> listCommentByBlogIdAndParentCommentNull(Integer blogId);

    Comment getCommentById(Long id);

    Integer saveComment(Comment comment);
//    List<Comment> listCommentByBlogId(Integer blogId);
}
