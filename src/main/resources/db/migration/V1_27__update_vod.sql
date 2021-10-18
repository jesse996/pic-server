ALTER TABLE sys_vod_detail
    ADD COLUMN create_time DATETIME DEFAULT NOW();
ALTER TABLE sys_vod_detail
    ADD COLUMN update_time DATETIME DEFAULT NOW();

ALTER TABLE sys_vod
    ADD COLUMN create_time DATETIME DEFAULT NOW();
ALTER TABLE sys_vod
    ADD COLUMN update_time DATETIME DEFAULT NOW();

CREATE TABLE sys_vod_class
(
    type_id     INTEGER NOT NULL,
    type_name   VARCHAR(255),
    create_time DATETIME DEFAULT NOW(),
    update_time DATETIME DEFAULT NOW(),
    PRIMARY KEY (type_id)
);