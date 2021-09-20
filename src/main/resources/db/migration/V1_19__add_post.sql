CREATE TABLE sys_post
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(255) COMMENT '标题',
    `vod_id`      BIGINT COMMENT '文章对应的电影id',
    `create_time` datetime DEFAULT NOW(),
    `update_time` datetime DEFAULT NOW(),
    PRIMARY KEY (`id`),
    INDEX (vod_id)
)COMMENT '文章表';





