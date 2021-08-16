ALTER TABLE pic
    DROP COLUMN name;
ALTER TABLE pic
    MODIFY COLUMN type TINYINT DEFAULT 0 COMMENT '0:性感妹子,1:二次元,2:cosplay,3:清纯妹子';

