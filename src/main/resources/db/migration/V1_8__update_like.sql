ALTER TABLE sys_like
    DROP COLUMN pic_id;
ALTER TABLE sys_like
    DROP COLUMN new_id;
ALTER TABLE sys_like
    ADD COLUMN type TINYINT NOT NULL COMMENT '0:pic;1:news';
ALTER TABLE sys_like
    ADD COLUMN obj_id BIGINT NOT NULL COMMENT '点赞对象id';

