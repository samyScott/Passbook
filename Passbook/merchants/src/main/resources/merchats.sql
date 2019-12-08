CREATE TABLE `merchants`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) COLLATE utf8_bin not null comment '商户名称',
    `logo_url` varchar(256) collate utf8_bin not null comment '商户 logo',
    `business_license_url` varchar(256) collate utf8_bin not null comment '商户营业执照',
    `address` varchar(64) collate utf8_bin not null comment '商户地址',
    `is_audit` BOOLEAN not null comment '是否通过审核',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;