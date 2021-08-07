ALTER TABLE pic
    CHANGE COLUMN cover_url cover_img VARCHAR(100) COMMENT '封面图';
ALTER TABLE pic
    CHANGE COLUMN url_list img_list JSON COMMENT '图片列表';