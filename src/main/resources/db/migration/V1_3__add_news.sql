CREATE TABLE news
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `cover_url`   VARCHAR(255) COMMENT '封面图片url',
    `title`       VARCHAR(100) COMMENT '标题',
    `content`     TEXT COMMENT '内容',
    `create_time` DATETIME DEFAULT NOW(),
    `update_time` DATETIME DEFAULT NOW(),
    `deleted`     TINYINT  DEFAULT 0,
    PRIMARY KEY (`id`)
) COMMENT '轮播图表';
