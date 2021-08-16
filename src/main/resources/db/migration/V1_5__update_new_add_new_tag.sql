ALTER TABLE news
    ADD COLUMN category_id BIGINT COMMENT '类别id';

CREATE TABLE category
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(50),
    `create_time` datetime DEFAULT NOW(),
    `update_time` datetime DEFAULT NOW(),
    PRIMARY KEY (`id`)
) COMMENT '类别表';

CREATE TABLE news_tag
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT,
    `news_id` BIGINT NOT NULL,
    `tag_id`  BIGINT NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE tag
    MODIFY COLUMN type TINYINT DEFAULT 0 COMMENT '0:pic,1:news';
