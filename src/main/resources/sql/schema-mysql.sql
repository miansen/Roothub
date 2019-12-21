CREATE TABLE IF NOT EXISTS `admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `uk_admin_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员表';

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

CREATE TABLE IF NOT EXISTS `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) NOT NULL DEFAULT '',
  `permission_value` varchar(255) NOT NULL DEFAULT '',
  `pid` int(11) NOT NULL DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `permission_name_uk` (`permission_name`),
  UNIQUE KEY `permission_value_uk` (`permission_value`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限表';

CREATE TABLE IF NOT EXISTS `admin_user_role_rel` (
  `admin_user_role_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_user_role_rel_id`),
  KEY `key_admin_user_role_rel_role_id` (`role_id`) USING BTREE,
  KEY `key_admin_user_role_rel_admin_user_id` (`admin_user_id`) USING BTREE,
  CONSTRAINT `fk_admin_user_role_rel_admin_user_id` FOREIGN KEY (`admin_user_id`) REFERENCES `admin_user` (`admin_user_id`),
  CONSTRAINT `fk_admin_user_role_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色用户关系表';

CREATE TABLE IF NOT EXISTS `role_permission_rel` (
  `role_permission_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_permission_rel_id`),
  KEY `key_role_permission_rel_role_id` (`role_id`) USING BTREE,
  KEY `key_role_permission_rel_permission_id` (`permission_id`) USING BTREE,
  CONSTRAINT `fk_role_permission_rel_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `fk_role_permission_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限关系表';

CREATE TABLE IF NOT EXISTS `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

CREATE TABLE IF NOT EXISTS `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '关注者ID',
  `fid` int(11) DEFAULT NULL COMMENT '被关注者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

CREATE TABLE IF NOT EXISTS `node` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `node_code` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '节点编码',
  `node_title` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '节点名称',
  `avatar_normal` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点头像',
  `avatar_mini` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点小头像',
  `avatar_large` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点背景头像',
  `node_desc` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '节点描述',
  `tab_id` int(11) DEFAULT NULL COMMENT '板块ID',
  `url` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT 'url',
  `parent_node_code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '父节点',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0:否 1:是',
  PRIMARY KEY (`node_id`),
  UNIQUE KEY `uk_node_title` (`node_title`),
  KEY `key_node_code` (`node_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='节点表';

CREATE TABLE IF NOT EXISTS `node_tab` (
  `node_tab_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点板块id',
  `node_tab_code` varchar(45) DEFAULT NULL COMMENT '节点板块编码',
  `node_tab_title` varchar(45) DEFAULT NULL COMMENT '节点板块名称',
  `node_tab_desc` varchar(300) DEFAULT NULL COMMENT '板块描述',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除，0:否 1:是',
  `node_tab_order` int(11) DEFAULT NULL COMMENT '节点板块排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`node_tab_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='节点板块表';

CREATE TABLE IF NOT EXISTS `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息通知标识',
  `notice_title` varchar(200) DEFAULT '' COMMENT '通知标题',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读：0:默认 1:已读',
  `notice_author_id` int(11) DEFAULT NULL COMMENT '发起通知用户id',
  `notice_author_name` varchar(50) NOT NULL DEFAULT '' COMMENT '发起通知用户昵称',
  `target_author_id` int(11) DEFAULT NULL COMMENT '要通知用户id',
  `target_author_name` varchar(50) NOT NULL DEFAULT '' COMMENT '要通知用户的昵称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '阅读时间',
  `notice_action` varchar(255) NOT NULL DEFAULT '' COMMENT '通知动作',
  `topic_id` int(11) DEFAULT NULL COMMENT '话题id',
  `topic_author_id` int(11) DEFAULT NULL COMMENT '话题作者id',
  `topic_section_id` int(11) DEFAULT NULL COMMENT '话题板块id',
  `notice_content` longtext COMMENT '通知内容',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '通知状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

CREATE TABLE IF NOT EXISTS `reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复标识',
  `topic_id` int(11) DEFAULT NULL COMMENT '话题id',
  `topic_author_id` int(11) DEFAULT NULL COMMENT '话题作者id',
  `reply_content` longtext COMMENT '回复内容',
  `create_date` datetime DEFAULT NULL COMMENT '回复时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `reply_author_id` int(11) DEFAULT NULL COMMENT '当前回复用户id',
  `reply_author_name` varchar(50) DEFAULT NULL COMMENT '当前回复用户昵称',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除 0:默认 1:删除',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读 0:默认 1:未读',
  `is_show` tinyint(1) DEFAULT '0' COMMENT '是否可见 0:默认 1:不可见',
  `reply_good_count` int(10) DEFAULT '0' COMMENT '点赞',
  `reply_bad_count` int(10) DEFAULT '0' COMMENT '踩数',
  `reply_type` varchar(16) DEFAULT NULL COMMENT '回复类型',
  `reply_read_count` int(11) DEFAULT NULL COMMENT '回复阅读数量',
  `status_cd` varchar(4) DEFAULT '1000' COMMENT '回复状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`reply_id`),
  KEY `key_topic_id` (`topic_id`),
  KEY `key_reply_author_name` (`reply_author_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='回复表';

CREATE TABLE IF NOT EXISTS `system_config` (
  `system_config_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `description` varchar(1000) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `type` varchar(255) DEFAULT NULL,
  `option` varchar(255) DEFAULT NULL,
  `reboot` int(11) NOT NULL DEFAULT '0',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除 0: 否 1: 是',
  PRIMARY KEY (`system_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='系统配置表';

CREATE TABLE IF NOT EXISTS `tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `tab_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `tab_desc` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0：否 1：是',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `tab_order` int(2) DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='父板块表';

CREATE TABLE IF NOT EXISTS `topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `ptab` varchar(50) DEFAULT NULL COMMENT '父板块',
  `tab` varchar(50) DEFAULT NULL COMMENT '版块',
  `title` varchar(250) NOT NULL COMMENT '标题',
  `tag` varchar(250) DEFAULT NULL COMMENT '标签',
  `content` longtext COMMENT '正文',
  `excerpt` varchar(500) DEFAULT NULL COMMENT '摘录',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `last_reply_time` datetime DEFAULT NULL COMMENT '最后回复时间，用于排序',
  `last_reply_author` varchar(50) DEFAULT NULL COMMENT '最后回复用户',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览量',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `top` tinyint(1) DEFAULT '0' COMMENT '1置顶 0默认',
  `good` tinyint(1) DEFAULT '0' COMMENT '1精华 0默认',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '1显示 0不显示',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '1删除 0默认',
  `tag_is_count` int(1) DEFAULT '0' COMMENT '文章内容标签是否被统计过 1是 0否默认',
  `post_good_count` int(10) DEFAULT '0' COMMENT '点赞',
  `post_bad_count` int(10) DEFAULT '0' COMMENT '踩数',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '文章状态 1000:有效 1100:无效 1200:未生效',
  `node_slug` varchar(50) DEFAULT NULL COMMENT '节点编码',
  `node_title` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `avatar` varchar(250) DEFAULT NULL COMMENT '头图',
  `url` varchar(250) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`topic_id`),
  KEY `key_tab` (`tab`),
  KEY `key_author` (`author`),
  KEY `key_node_title` (`node_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='帖子表';

CREATE TABLE IF NOT EXISTS `up_down` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `up_down` tinyint(1) DEFAULT NULL COMMENT '0:down 1:up',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `user_name` varchar(50) NOT NULL COMMENT '昵称',
  `password` varchar(250) NOT NULL COMMENT '密码',
  `user_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `user_addr` varchar(250) DEFAULT NULL COMMENT '地址',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `url` varchar(255) DEFAULT NULL COMMENT '个人主页',
  `signature` varchar(1000) DEFAULT NULL COMMENT '个性签名',
  `third_id` varchar(50) DEFAULT NULL COMMENT '第三方账户id',
  `receive_msg` tinyint(1) DEFAULT NULL COMMENT '邮箱是否接收社区消息 0:默认 1:不接收',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `is_block` tinyint(1) DEFAULT NULL COMMENT '用户是否禁用 0:默认 1:禁用',
  `third_access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '用户状态 1000:有效 1100:无效 1200:未生效',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '用户登入ip',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '用户最后登入ip',
  `user_type` int(2) DEFAULT NULL COMMENT '用户类型 0:超级管理员 1:版主 2:普通用户',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UNIQUE_NAME` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '访问者ID',
  `vid` int(11) DEFAULT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问记录表';