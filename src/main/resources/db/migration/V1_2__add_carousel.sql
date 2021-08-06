CREATE TABLE carousel
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `url`         VARCHAR(255) COMMENT '图片url',
    `create_time` DATETIME DEFAULT NOW(),
    `update_time` DATETIME DEFAULT NOW(),
    `deleted`      TINYINT  DEFAULT 0,
    PRIMARY KEY (`id`)
) COMMENT '轮播图表';
