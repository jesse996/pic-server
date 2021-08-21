ALTER TABLE sys_order
    ADD COLUMN status TINYINT DEFAULT 0 COMMENT '0:待支付，1：支付成功，2：支付失败';


