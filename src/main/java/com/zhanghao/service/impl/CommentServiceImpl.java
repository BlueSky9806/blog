package com.zhanghao.service.impl;

import com.zhanghao.mapper.CommentMapper;
import com.zhanghao.po.Comment;
import com.zhanghao.service.CommentService;
import com.zhanghao.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final String PREFIX = "http://q1.qlogo.cn/g?b=qq&nk=";
    private static final String SUFFIX = "&s=100";

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Integer blogId) {
        // 找出所有一级评论
        List<Comment> topComments = commentMapper.listCommentByBlogIdAndParentCommentNull(blogId);
        // 遍历所有一级评论
        for (Comment comment : topComments) {
            List<Comment> fatherChildren = new ArrayList<>();
            // 递归处理子级的回复，即回复内有回复
            findChildren(comment, fatherChildren);
            // 将递归处理后的集合放回父级的孩子中
            comment.setReplyComments(fatherChildren);
        }
        return topComments;
    }

    // https://github.com/hellokuls/blog/blob/master/src/main/java/com/kuls/service/CommentServiceImpl.java
    // 遍历子评论
    private void findChildren(Comment comment, List<Comment> fatherChildren) {
        // 一级评论（相对于评论下的回复来说）
        List<Comment> replyComments = comment.getReplyComments();

        // 遍历一级评论的回复
        for (Comment replyComment : replyComments) {
            // 非空，则还有回复的回复，递归
            if (!replyComment.getReplyComments().isEmpty()) {
                findChildren(replyComment, fatherChildren);
            }
            // 已经到了最底层的嵌套关系，将该回复放入新建立的集合
            fatherChildren.add(replyComment);
            // 将回复放入新建的集合之后，则表示解除了嵌套关系，对应的其父级的子级应该设为空
            replyComment.setReplyComments(null);
        }
    }

    @Transactional
    @Override
    public Integer saveComment(Comment comment) {
        //http://q1.qlogo.cn/g?b=qq&nk=你的QQ号&s=100
        Long parentCommentId = comment.getParentComment().getId();
        // 判断是父级评论还是子级评论
        if (parentCommentId != -1) {
            Comment parentComment = new Comment();
            parentComment.setId(parentCommentId);
            comment.setParentComment(parentComment);

            // 这里必须根据parentCommentId从数据库中找然后给comment中的parentComment赋值
            comment.setParentComment(commentMapper.getCommentById(parentCommentId));
        } else {
            comment.setParentComment(null);
        }
        comment.setAvatar(PREFIX + StringUtils.getQQNumber(comment.getEmail()) + SUFFIX);
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    /*private List<Comment> findTopCommentsByParentCommentId(List<Comment> comments) {
        // 找出所有顶级评论
        List<Comment> topComments = comments.stream()
                .filter(commentPO -> null == commentPO.getParentComment())
                .collect(Collectors.toList());
        // 找出所有子级评论
        List<Comment> subComments = comments.stream()
                .filter(commentPO -> null != commentPO.getParentComment())
                .collect(Collectors.toList());
        // 一个顶级评论可能有多个子级评论
        for (Comment topComment : topComments) {
            List<Comment> attach = new ArrayList<>();
            // 在所有子级评论找出所属顶级评论
            for (Comment subComment : subComments) {
                // 当前顶级评论的id 与所有直接子级评论的id 作比较 相等则添加到顶级评论中的replyComments
                if (subComment.getParentComment().getId().equals(topComment.getId())) {
                    attach.add(subComment);
                }
            }
            topComment.setReplyComments(attach);
        }
        return topComments;
    }*/
}
