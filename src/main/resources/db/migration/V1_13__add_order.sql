CREATE TABLE sys_order
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT NOT NULL COMMENT '用户id',
    `amount`      BIGINT NOT NULL COMMENT '金额，精确到后面2位',
    `target_id`   BIGINT NOT NULL COMMENT '订单目标id',
    `type`        TINYINT COMMENT '0：赞赏作品，1：赞赏文章,2:买作品',
    `create_time` datetime DEFAULT NOW(),
    `update_time` datetime DEFAULT NOW(),
    PRIMARY KEY (`id`),
    INDEX (user_id)
)

