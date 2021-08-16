CREATE TABLE view_count
(
    `id`          BIGINT  NOT NULL AUTO_INCREMENT,
    `type`        TINYINT NOT NULL DEFAULT 0 COMMENT '0:pic,1:文章',
    `target_id`   BIGINT  NOT NULL COMMENT 'pic id或news id',
    `count`       INT     NOT NULL DEFAULT 0 COMMENT '浏览次数',
    `create_time` datetime         DEFAULT NOW(),
    `update_time` datetime         DEFAULT NOW(),
    PRIMARY KEY (`id`)
) COMMENT '浏览量表';

