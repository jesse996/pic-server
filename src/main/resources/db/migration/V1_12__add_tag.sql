CREATE TABLE tag
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50) NOT NULL COMMENT 'tag名',
    `cover_img`   VARCHAR(255) COMMENT '封面图',
    `type`        TINYINT  DEFAULT 0 COMMENT '0:类别，1：专辑',
    `create_time` DATETIME DEFAULT NOW(),
    `update_time` DATETIME DEFAULT NOW(),
    `deleted`     TINYINT  DEFAULT 0,
    PRIMARY KEY (`id`)
) COMMENT '标签表';

CREATE TABLE pic_tag
(
    `id`     BIGINT       NOT NULL AUTO_INCREMENT,
    `pic_id` VARCHAR(50)  NOT NULL,
    `tag_id` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT 'pic_tag表';
