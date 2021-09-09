ALTER TABLE sys_order
    MODIFY COLUMN target_id BIGINT COMMENT '目标id，为null时代表时充值vip';

ALTER TABLE sys_order
    MODIFY COLUMN type TINYINT COMMENT '0：赞赏作品，1：赞赏文章,2:买作品，3:充值Vip';

ALTER TABLE sys_order
    ADD COLUMN extra VARCHAR(100) COMMENT 'type为3时代表充值月数';






