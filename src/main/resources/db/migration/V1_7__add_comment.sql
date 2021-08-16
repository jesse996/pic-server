CREATE TABLE comment
(
    `id`            BIGINT        NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT        NOT NULL,
    `content`       VARCHAR(1000) NOT NULL,
    `to_comment_id` BIGINT COMMENT '回复的评论id，主评论为null',
    `create_time`   datetime DEFAULT NOW(),
    `update_time`   datetime DEFAULT NOW(),
    PRIMARY KEY (`id`),
    INDEX (user_id)
) COMMENT '评论表';

CREATE TABLE sys_like
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT NOT NULL,
    `pic_id`      BIGINT,
    `new_id`      BIGINT,
    `create_time` datetime DEFAULT NOW(),
    `update_time` datetime DEFAULT NOW(),
    PRIMARY KEY (`id`),
    INDEX (user_id)
) COMMENT '点赞表';