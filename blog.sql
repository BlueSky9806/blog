CREATE TABLE tb_blog (
 id BIGINT(11) COMMENT '主键',
 title VARCHAR(50) COMMENT '博客标题',
 content TEXT COMMENT '博客内容',
 firstPicture VARCHAR(255) COMMENT '图片地址',
 flag VARCHAR(255) COMMENT '标记',
 views INT(11) COMMENT '博客浏览次数',
 appreciation BIT COMMENT '是否开启赞赏',
 shareStatement BIT COMMENT '版权开启',
 comment BIT COMMENT '评论开启',
 published BIT COMMENT '博客是否发布',
 recommend BIT COMMENT '是否推荐',
 create_time DATETIME COMMENT '博客发布时间',
 update_time DATETIME COMMENT '博客修改时间',
 type_id BIGINT(20) COMMENT '与分类表ID关联',
 user_id BIGINT(20) COMMENT '与用户表ID关联',
 PRIMARY KEY(id)
);

CREATE TABLE tb_comment (
 id BIGINT(11) COMMENT '主键',
 nick_name VARCHAR(255) COMMENT '',
 email VARCHAR(255) COMMENT '邮箱地址',
 content VARCHAR(255) COMMENT '评论内容',
 avatar VARCHAR(255) COMMENT '头像',
 create_time DATETIME COMMENT '评论时间',
 blog_id BIGINT COMMENT '与博客表ID关联',
 parent_comment_id BIGINT COMMENT '自关联',
 PRIMARY KEY(id)
);

CREATE TABLE tb_user (
 id BIGINT(11) COMMENT '主键',
 nick_name VARCHAR(255) COMMENT '昵称',
 username VARCHAR(25) COMMENT '用户名',
 password VARCHAR(255) COMMENT '密码',
 email VARCHAR(255) COMMENT '邮箱',
 avatar VARCHAR(255) COMMENT '头像',
 type INT COMMENT '类型',
 create_time DATETIME COMMENT '创建时间',
 update_time DATETIME COMMENT '更新时间',
 PRIMARY KEY(id)
);

CREATE TABLE tb_type (
 id BIGINT(20) COMMENT '主键',
 name VARCHAR(255) COMMENT '',
 PRIMARY KEY(id)
);