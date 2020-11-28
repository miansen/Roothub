CREATE TABLE `collect` (
  `collect_id` varchar(36) NOT NULL COMMENT '收藏ID',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `post_id` varchar(36) NOT NULL COMMENT '帖子ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`collect_id`),
  KEY `key_user_id` (`user_id`),
  KEY `key_post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

CREATE TABLE `comment` (
  `comment_id` varchar(36) NOT NULL COMMENT '评论ID',
  `post_id` varchar(36) NOT NULL COMMENT '帖子ID',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `content` longtext COMMENT '评论内容',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览量',
  `share_count` int(11) DEFAULT '0' COMMENT '转载量',
  `good_count` int(11) DEFAULT '0' COMMENT '好评量',
  `bad_count` int(11) DEFAULT '0' COMMENT '差评量',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `type` varchar(16) DEFAULT NULL COMMENT '类型，1000（文本）、1100（图片）、1200（视频）、1300（链接）',
  `status` varchar(4) DEFAULT '1000' COMMENT '状态，1000（有效）、1100（无效）、1200（未生效）',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`comment_id`),
  KEY `key_user_id` (`user_id`),
  KEY `key_post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评论表';

CREATE TABLE `follow` (
  `follow_id` varchar(36) NOT NULL COMMENT '关注ID',
  `source_id` varchar(36) NOT NULL COMMENT '关注者ID',
  `target_id` varchar(36) NOT NULL COMMENT '被关注者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`follow_id`),
  KEY `key_source_id` (`source_id`),
  KEY `key_target_id` (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注表';

CREATE TABLE `node` (
  `node_id` int(11) NOT NULL COMMENT '主键ID',
  `parent_node_id` int(11) DEFAULT NULL COMMENT '父节点ID',
  `node_code` varchar(50) DEFAULT NULL COMMENT '节点编码',
  `node_name` varchar(50) NOT NULL COMMENT '节点名称',
  `node_desc` varchar(2000) DEFAULT NULL COMMENT '节点描述',
  `avatar` varchar(250) DEFAULT NULL COMMENT '节点图标',
  `url` varchar(50) DEFAULT NULL COMMENT 'url',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`node_id`),
  UNIQUE KEY `uk_node_name` (`node_name`),
  KEY `key_parent_node_id` (`parent_node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节点表';

CREATE TABLE `notice` (
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

CREATE TABLE `permission` (
  `permission_id` varchar(36) NOT NULL,
  `permission_name` varchar(250) NOT NULL,
  `permission_value` varchar(250) NOT NULL,
  `permission_desc` varchar(500) DEFAULT NULL,
  `parent_permission_id` varchar(36) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission_name` (`permission_name`),
  UNIQUE KEY `uk_permission_value` (`permission_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限表';

CREATE TABLE `post` (
  `post_id` varchar(36) NOT NULL COMMENT '帖子ID',
  `node_id` int(11) DEFAULT NULL COMMENT '节点ID',
  `user_id` int(11) DEFAULT NULL COMMENT '作者ID',
  `title` varchar(250) NOT NULL COMMENT '标题',
  `content` longtext COMMENT '正文',
  `excerpt` varchar(500) DEFAULT NULL COMMENT '摘录',
  `avatar` varchar(250) DEFAULT NULL COMMENT '封面',
  `url` varchar(250) DEFAULT NULL COMMENT '链接',
  `top` tinyint(1) DEFAULT '0' COMMENT '1置顶 0默认',
  `good` tinyint(1) DEFAULT '0' COMMENT '1精华 0默认',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览量',
  `comment_count` int(11) DEFAULT '0' COMMENT '评论量',
  `share_count` int(11) DEFAULT '0' COMMENT '转载量',
  `good_count` int(11) DEFAULT '0' COMMENT '好评量',
  `bad_count` int(11) DEFAULT '0' COMMENT '差评量',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `type` int(11) DEFAULT NULL COMMENT '类型，1000（文本）、1100（图片）、1200（视频）、1300（链接）',
  `status` int(11) DEFAULT NULL COMMENT '状态，1000（有效）、1100（无效）、1200（未生效）',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`),
  KEY `key_node_id` (`node_id`),
  KEY `key_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='帖子表';

CREATE TABLE `role` (
  `role_id` varchar(36) NOT NULL,
  `role_name` varchar(250) NOT NULL,
  `role_desc` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

CREATE TABLE `role_permission_rel` (
  `role_permission_rel_id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  `permission_id` varchar(36) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_permission_rel_id`),
  KEY `key_role_permission_rel_role_id` (`role_id`),
  KEY `key_role_permission_rel_permission_id` (`permission_id`),
  CONSTRAINT `fk_role_permission_rel_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `fk_role_permission_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限关联关系表';

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `parent_menu_id` int(11) DEFAULT NULL COMMENT '父级菜单 ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限 ID',
  `user_id` int(11) DEFAULT NULL COMMENT '创建人 ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(500) DEFAULT NULL COMMENT '点击菜单时的请求 URL',
  `menu_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`),
  KEY `key_menu_parent_menu_id` (`parent_menu_id`),
  KEY `key_menu_permission_id` (`permission_id`),
  KEY `key_menu_user_id` (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表'

CREATE TABLE `system_config` (
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签主键',
  `tag_name` varchar(25) CHARACTER SET utf8 NOT NULL COMMENT '标签名称',
  `tag_state` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签状态',
  `tag_avatar` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签图标',
  `tag_content` varchar(2500) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签简介',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`tag_id`),
  KEY `key_tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

CREATE TABLE `up_down` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `up_down` tinyint(1) DEFAULT NULL COMMENT '0:down 1:up',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

CREATE TABLE `user` (
  `user_id` varchar(36) NOT NULL COMMENT '用户标识',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

CREATE TABLE `user_role_rel` (
  `user_role_rel_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_rel_id`),
  KEY `key_user_role_rel_user_id` (`user_id`),
  KEY `key_user_role_rel_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `fk_user_role_rel_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联关系表';

CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '访问者ID',
  `vid` int(11) DEFAULT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='访问记录表';

CREATE TABLE `app` (
  `app_id` varchar(36) NOT NULL COMMENT '应用ID',
  `app_category_id` varchar(36) DEFAULT NULL COMMENT '应用类别ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `app_name` varchar(250) NOT NULL COMMENT '应用名称',
  `app_desc` varchar(500) DEFAULT NULL COMMENT '应用描述',
  `app_icon` varchar(250) DEFAULT NULL COMMENT '应用图标',
  `app_index` varchar(500) NOT NULL COMMENT '应用首页',
  `app_status` int(11) DEFAULT NULL COMMENT '应用状态(1000: "草稿", 1100: "发布", 1200: "停用")',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_id`),
  KEY `key_app_app_category_id` (`app_category_id`),
  KEY `key_app_user_id` (`user_id`),
  KEY `key_app_app_name` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表'

CREATE TABLE `app_category` (
  `app_category_id` VARCHAR(36) NOT NULL COMMENT '应用类别ID',
  `user_id` VARCHAR(36) DEFAULT NULL COMMENT '创建人ID',
  `app_category_name` VARCHAR(250) NOT NULL COMMENT '应用类别名称',
  `app_category_desc` VARCHAR(500) DEFAULT NULL COMMENT '应用类别描述',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_category_id`),
  KEY `key_app_category_user_id` (`user_id`),
  KEY `key_app_category_app_category_name` (`app_category_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='应用类别表'

CREATE TABLE `resource_type` (
  `resource_type_id` varchar(36) NOT NULL COMMENT '资源类型ID',
  `resource_type_code` int(11) DEFAULT NULL COMMENT '资源类型编码',
  `resource_type_name` varchar(50) NOT NULL COMMENT '资源类型名称',
  PRIMARY KEY (`resource_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源类型表'

CREATE TABLE `resource_category` (
  `resource_category_id` varchar(36) NOT NULL COMMENT '资源类别ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `resource_category_name` varchar(50) NOT NULL COMMENT '资源类别名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`resource_category_id`),
  KEY `key_resource_category_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源类别表'

CREATE TABLE `resource` (
  `resource_id` varchar(36) NOT NULL COMMENT '资源ID',
  `resource_type_id` varchar(36) DEFAULT NULL COMMENT '资源类型ID',
  `resource_category_id` varchar(36) DEFAULT NULL COMMENT '资源类别ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名称',
  `resource_value` varchar(500) NOT NULL COMMENT '资源值',
  `resource_desc` varchar(500) DEFAULT NULL COMMENT '资源描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`resource_id`),
  KEY `key_r_resource_type_id` (`resource_type_id`),
  KEY `key_r_resource_category_id` (`resource_category_id`),
  KEY `key_r_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表'