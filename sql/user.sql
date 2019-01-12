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