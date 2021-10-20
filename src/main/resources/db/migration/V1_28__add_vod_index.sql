ALTER TABLE sys_vod
    ADD INDEX (vod_name);
ALTER TABLE sys_vod
    ADD INDEX (type_id);
ALTER TABLE sys_vod
    ADD INDEX (vod_time, update_time);

ALTER TABLE sys_vod_detail
    ADD INDEX (vod_name);
ALTER TABLE sys_vod_detail
    ADD INDEX (type_id);
ALTER TABLE sys_vod_detail
    ADD INDEX (vod_time, update_time);