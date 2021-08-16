CREATE TABLE user
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT,
    `nickname`    VARCHAR(50) NOT NULL COMMENT '昵称',
    `username`    VARCHAR(20) NOT NULL UNIQUE KEY COMMENT '用户名（邮箱）',
    `create_time` datetime DEFAULT NOW(),
    `update_time` datetime DEFAULT NOW(),
    PRIMARY KEY (`id`)
) COMMENT '用户表';