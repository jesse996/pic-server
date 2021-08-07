CREATE TABLE pic_list
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    url_list    JSON COMMENT '图片url列表',
    create_time DATETIME DEFAULT NOW(),
    update_time DATETIME DEFAULT NOW(),
    deleted     TINYINT  DEFAULT 0,
    PRIMARY KEY (id)
) COMMENT '图片列表详情';