create table pic
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL,
    `url`         VARCHAR(255)          NOT NULL COMMENT '图片url',
    `title`       VARCHAR(100) COMMENT '标题',
    `description` VARCHAR(255) COMMENT '描述',
    `src`         VARCHAR(255) COMMENT '来源',
    `name`        VARCHAR(50) COMMENT '名字',
    `type`        TINYINT COMMENT '0:三次元，1：二次元，2：cosplay',
    `create_time` DATETIME,
    `update_time` DATETIME,
    PRIMARY KEY (`id`)
)