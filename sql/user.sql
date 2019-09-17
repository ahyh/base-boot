CREATE TABLE `user` (
  `id` bigint(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `phone` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '电话号码',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮件',
  `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户类型：0-普通用户，1-VIP用户，2-超级VIP用户',
  `user_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户状态：0-正常，1-锁定，2-冷冻',
  `is_delete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态：0-正常，1-删除',
  `create_user` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_user` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`),
  KEY `unique_createtime` (`create_time`),
  KEY `unique_updatetime` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci


CREATE TABLE `salary` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `age` int(5) NOT NULL COMMENT '年龄',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别：0-男；1-女',
  `company` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `salary` decimal(11,2) DEFAULT NULL COMMENT '薪水',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(30) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '删除标识（1-删除，0-未删除）',
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name_age_sex` (`name`,`age`,`sex`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8 COMMENT='薪水'


-- user
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('1','yanhuan','123456','176112345667','ahyanhuan@163.com','1','1','0','yanhuan','2019-01-12 23:43:00','yanhuan','2019-01-12 23:43:00','2019-01-12 23:43:00');
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('2','string','string','string','string','0','0','0','yanhuan','2019-01-13 16:16:56','yanhuan','2019-01-13 16:16:56','2019-01-13 16:16:56');
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('5','yu','MTIzNDU2','123456789','string','0','0','0','yanhuan','2019-01-13 19:16:08','yanhuan','2019-01-13 19:16:08','2019-01-13 19:16:08');
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('6','yh','123456','18611862917','ahyanhuan@sina.com','0','0','0','yanhuan','2019-07-21 12:42:42','yanhuan','2019-07-21 12:42:42','2019-07-21 12:42:42');
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('7','huanyan','123456','18611862917','ahyanhuan@sina.com','2','1','0','huanyan','2019-09-13 23:06:12','huanyan','2019-09-13 23:06:12','2019-09-13 23:06:12');
insert into `user` (`id`, `username`, `password`, `phone`, `email`, `user_type`, `user_status`, `is_delete`, `create_user`, `create_time`, `update_user`, `update_time`, `ts`) values('8','u1','123456','18611862917','ahyanhuan@126.com','2','1','0','yh','2019-09-17 23:50:53','yh','2019-09-17 23:50:53','2019-09-17 23:50:53');

-- salary
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('12','liuan','26','1','jjjjjjjdddddddd....com','22222.00','2018-09-23 01:03:25','2018-09-23 01:03:25','yanhuan','yanhuan','0','2018-09-23 01:03:25');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('13','小张0','1','1','jd.com','1730.52','2018-09-23 12:10:46','2018-09-23 12:10:47','yanhuan','yanhuan','0','2018-09-23 12:10:47');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('14','小张1','1','1','tx.com','1731.96','2018-09-23 12:10:46','2018-09-23 12:10:46','yanhuan','yanhuan','0','2018-09-23 12:10:46');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('15','小张2','1','1','jd.com','1141.98','2018-09-23 12:10:46','2018-09-23 12:10:47','yanhuan','yanhuan','0','2018-09-23 12:10:47');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('16','小张3','1','1','tx.com','1239.18','2018-09-23 12:10:46','2018-09-23 12:10:46','yanhuan','yanhuan','0','2018-09-23 12:10:46');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('17','小张4','1','1','jd.com','216.10','2018-09-23 12:10:46','2018-09-23 12:10:47','yanhuan','yanhuan','0','2018-09-23 12:10:47');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('18','小张5','1','1','tx.com','1776.31','2018-09-23 12:10:46','2018-09-23 12:10:46','yanhuan','yanhuan','0','2018-09-23 12:10:46');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('19','小张6','1','1','jd.com','1275.61','2018-09-23 12:10:46','2018-09-23 12:10:47','yanhuan','yanhuan','0','2018-09-23 12:10:47');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('20','小张7','1','1','tx.com','444.28','2018-09-23 12:10:46','2018-09-23 12:10:46','yanhuan','yanhuan','0','2018-09-23 12:10:46');
insert into `salary` (`id`, `name`, `age`, `sex`, `company`, `salary`, `create_time`, `update_time`, `create_user`, `update_user`, `is_delete`, `ts`) values('21','小张8','1','1','jd.com','1667.22','2018-09-23 12:10:46','2018-09-23 12:10:47','yanhuan','yanhuan','0','2018-09-23 12:10:47');
