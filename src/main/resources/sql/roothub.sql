/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : roothub

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-01-02 12:58:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_user_id`),
  UNIQUE KEY `uk_admin_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员表';

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', 'admin', 'c41d7c66e1b8404545aa3a0ece2006ac', '/resources/images/default-avatar.jpg', '2019-02-26 11:09:57', null);

-- ----------------------------
-- Table structure for `admin_user_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role_rel`;
CREATE TABLE `admin_user_role_rel` (
  `admin_user_role_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_user_role_rel_id`),
  KEY `key_admin_user_role_rel_role_id` (`role_id`) USING BTREE,
  KEY `key_admin_user_role_rel_admin_user_id` (`admin_user_id`) USING BTREE,
  CONSTRAINT `fk_admin_user_role_rel_admin_user_id` FOREIGN KEY (`admin_user_id`) REFERENCES `admin_user` (`admin_user_id`),
  CONSTRAINT `fk_admin_user_role_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_bak` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色用户关系表';

-- ----------------------------
-- Records of admin_user_role_rel
-- ----------------------------
INSERT INTO `admin_user_role_rel` VALUES ('1', '1', '1', '2019-03-14 15:42:47', '2019-03-14 15:42:47');

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用表';

-- ----------------------------
-- Records of app
-- ----------------------------

-- ----------------------------
-- Table structure for `app_category`
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category` (
  `app_category_id` varchar(36) NOT NULL COMMENT '应用类别ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `app_category_name` varchar(250) NOT NULL COMMENT '应用类别名称',
  `app_category_desc` varchar(500) DEFAULT NULL COMMENT '应用类别描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`app_category_id`),
  KEY `key_app_category_user_id` (`user_id`),
  KEY `key_app_category_app_category_name` (`app_category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用类别表';

-- ----------------------------
-- Records of app_category
-- ----------------------------

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
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

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
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

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
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

-- ----------------------------
-- Records of follow
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, null, null, '角色管理', '#', null, '1', '2020-11-28 17:33:31', '2020-11-28 17:33:31');
INSERT INTO `menu` VALUES ('2', '1', null, null, '角色列表', '/admin/role/list', null, '2', '2020-11-28 17:34:06', '2020-11-28 17:34:06');
INSERT INTO `menu` VALUES ('3', '1', null, null, '角色添加', '/admin/role/add', null, '3', '2020-11-28 17:34:38', '2020-11-28 17:34:38');
INSERT INTO `menu` VALUES ('4', null, null, null, '权限管理', '#', null, '4', '2020-11-28 17:34:50', '2020-11-28 17:34:55');
INSERT INTO `menu` VALUES ('5', '4', null, null, '权限列表', '/admin/permission/list', null, '5', '2020-11-28 17:35:51', '2020-11-28 17:35:51');
INSERT INTO `menu` VALUES ('6', '4', null, null, '权限添加', '/admin/permission/add', null, '6', '2020-11-28 17:36:19', '2020-11-28 17:36:19');

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `system_id` bigint(20) NOT NULL COMMENT '系统ID',
  `module_name` varchar(50) NOT NULL COMMENT '模块编码',
  `module_code` varchar(50) NOT NULL COMMENT '模块名称',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`module_id`),
  KEY `idx_system_id` (`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模块表';

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', '5', '首页', 'biz:index', '0', '0', '2020-12-22 20:41:01', '2020-12-22 20:41:01');
INSERT INTO `module` VALUES ('2', '5', '帖子', 'biz:post', '0', '0', '2020-12-22 20:41:01', '2020-12-22 20:41:01');
INSERT INTO `module` VALUES ('3', '5', '评论', 'biz:comment', '0', '0', '2020-12-22 20:41:01', '2020-12-22 20:41:01');

-- ----------------------------
-- Table structure for `node`
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='节点表';

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('1', null, 'java', 'Java', 'Java语言', '/resources/images/node/java_normal.png', '/n/Java', '2018-11-03 11:57:18', '2019-04-23 23:48:45');

-- ----------------------------
-- Table structure for `node_tab`
-- ----------------------------
DROP TABLE IF EXISTS `node_tab`;
CREATE TABLE `node_tab` (
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

-- ----------------------------
-- Records of node_tab
-- ----------------------------
INSERT INTO `node_tab` VALUES ('1', 'all', '全部', '', '0', '1', '2018-05-12 10:04:43', null);
INSERT INTO `node_tab` VALUES ('2', 'good', '精华', '', '0', '2', '2018-05-12 12:03:30', null);
INSERT INTO `node_tab` VALUES ('3', 'newest', '最新', '', '0', '3', '2018-05-12 14:47:35', null);
INSERT INTO `node_tab` VALUES ('4', 'noReply', '等待评论', '', '0', '4', '2018-05-12 14:55:30', null);

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '父权限ID',
  `permission_name` varchar(50) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(50) NOT NULL COMMENT '权限编码',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`permission_id`),
  KEY `idx_module_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '1', '0', '首页', 'biz:index', '0', '0', '2020-12-27 18:50:42', '2020-12-27 18:50:42');

-- ----------------------------
-- Table structure for `permission_bak`
-- ----------------------------
DROP TABLE IF EXISTS `permission_bak`;
CREATE TABLE `permission_bak` (
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

-- ----------------------------
-- Records of permission_bak
-- ----------------------------
INSERT INTO `permission_bak` VALUES ('1', '首页', 'index', '0', '2019-02-26 11:29:05', null);
INSERT INTO `permission_bak` VALUES ('2', '话题', 'topic', '0', '2019-02-26 11:29:07', null);
INSERT INTO `permission_bak` VALUES ('3', '评论', 'reply', '0', '2019-02-26 11:29:09', null);
INSERT INTO `permission_bak` VALUES ('4', '通知', 'notice', '0', '2019-02-26 11:29:12', null);
INSERT INTO `permission_bak` VALUES ('5', '用户', 'user', '0', '2019-02-26 11:29:15', null);
INSERT INTO `permission_bak` VALUES ('6', '节点', 'node', '0', '2019-02-26 11:29:18', null);
INSERT INTO `permission_bak` VALUES ('7', '标签', 'tag', '0', '2019-02-26 11:29:21', null);
INSERT INTO `permission_bak` VALUES ('8', '权限', 'permission', '0', '2019-02-26 11:29:23', null);
INSERT INTO `permission_bak` VALUES ('9', '系统', 'system', '0', '2019-02-26 11:29:26', null);
INSERT INTO `permission_bak` VALUES ('10', '后台用户', 'admin_user', '0', '2019-02-26 11:29:28', null);
INSERT INTO `permission_bak` VALUES ('11', '角色', 'role', '0', '2019-02-26 11:29:30', null);
INSERT INTO `permission_bak` VALUES ('12', '日志', 'log', '0', '2019-02-26 11:29:33', null);
INSERT INTO `permission_bak` VALUES ('13', '仪表盘', 'index:index', '1', '2019-02-26 11:29:35', '2019-03-06 14:36:11');
INSERT INTO `permission_bak` VALUES ('14', '话题列表', 'topic:list', '2', '2019-02-26 11:29:37', '2019-03-06 14:36:30');
INSERT INTO `permission_bak` VALUES ('15', '话题编辑', 'topic:edit', '2', '2019-02-26 11:29:40', null);
INSERT INTO `permission_bak` VALUES ('16', '话题删除', 'topic:delete', '2', '2019-02-26 11:29:42', null);
INSERT INTO `permission_bak` VALUES ('17', '话题加精', 'topic:good', '2', '2019-02-26 11:29:45', null);
INSERT INTO `permission_bak` VALUES ('18', '话题置顶', 'topic:top', '2', '2019-02-26 11:29:47', null);
INSERT INTO `permission_bak` VALUES ('19', '评论列表', 'reply:list', '3', '2019-03-03 14:22:21', null);
INSERT INTO `permission_bak` VALUES ('20', '评论编辑', 'reply:edit', '3', '2019-03-03 14:22:24', null);
INSERT INTO `permission_bak` VALUES ('21', '评论删除', 'reply:delete', '3', '2019-03-03 14:22:50', null);
INSERT INTO `permission_bak` VALUES ('22', '通知列表', 'notice:list', '4', '2019-03-03 14:23:28', null);
INSERT INTO `permission_bak` VALUES ('23', '通知删除', 'notice:delete', '4', '2019-03-03 14:23:51', null);
INSERT INTO `permission_bak` VALUES ('24', '用户列表', 'user:list', '5', '2019-03-03 14:24:15', null);
INSERT INTO `permission_bak` VALUES ('25', '用户编辑', 'user:edit', '5', '2019-03-03 14:24:29', null);
INSERT INTO `permission_bak` VALUES ('26', '用户删除', 'user:delete', '5', '2019-03-03 14:24:45', null);
INSERT INTO `permission_bak` VALUES ('27', '节点列表', 'node:list', '6', '2019-03-03 14:25:12', null);
INSERT INTO `permission_bak` VALUES ('28', '节点编辑', 'node:edit', '6', '2019-03-03 14:25:32', null);
INSERT INTO `permission_bak` VALUES ('29', '节点删除', 'node:delete', '6', '2019-03-03 14:25:49', null);
INSERT INTO `permission_bak` VALUES ('30', '标签列表', 'tag:list', '7', '2019-03-03 14:26:27', null);
INSERT INTO `permission_bak` VALUES ('31', '标签编辑', 'tag:edit', '7', '2019-03-03 14:26:41', null);
INSERT INTO `permission_bak` VALUES ('32', '标签删除', 'tag:delete', '7', '2019-03-03 14:26:55', null);
INSERT INTO `permission_bak` VALUES ('33', '权限列表', 'permission:list', '8', '2019-03-03 14:28:50', null);
INSERT INTO `permission_bak` VALUES ('34', '权限编辑', 'permission:edit', '8', '2019-03-03 14:29:03', null);
INSERT INTO `permission_bak` VALUES ('35', '权限删除', 'permission:delete', '8', '2019-03-03 14:29:16', null);
INSERT INTO `permission_bak` VALUES ('36', '系统设置', 'system:edit', '9', '2019-03-03 14:29:57', null);
INSERT INTO `permission_bak` VALUES ('37', '后台用户列表', 'admin_user:list', '10', '2019-03-03 14:30:24', null);
INSERT INTO `permission_bak` VALUES ('38', '后台用户编辑', 'admin_user:edit', '10', '2019-03-03 14:30:39', null);
INSERT INTO `permission_bak` VALUES ('39', '后台用户创建', 'admin_user:add', '10', '2019-03-03 14:30:55', null);
INSERT INTO `permission_bak` VALUES ('40', '后台用户删除', 'admin_user:delete', '10', '2019-03-03 14:31:25', null);
INSERT INTO `permission_bak` VALUES ('41', '角色列表', 'role:list', '11', '2019-03-03 14:31:54', null);
INSERT INTO `permission_bak` VALUES ('42', '角色编辑', 'role:edit', '11', '2019-03-03 14:32:24', null);
INSERT INTO `permission_bak` VALUES ('43', '角色创建', 'role:add', '11', '2019-03-03 14:32:50', null);
INSERT INTO `permission_bak` VALUES ('44', '角色删除', 'role:delete', '11', '2019-03-03 14:33:06', null);
INSERT INTO `permission_bak` VALUES ('45', '日志列表', 'log:list', '12', '2019-03-03 14:33:54', null);
INSERT INTO `permission_bak` VALUES ('47', '权限创建', 'permission:add', '8', '2019-03-05 16:11:20', null);

-- ----------------------------
-- Table structure for `permission_resource_rel`
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource_rel`;
CREATE TABLE `permission_resource_rel` (
  `permission_resource_rel_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`permission_resource_rel_id`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='权限资源关联表';

-- ----------------------------
-- Records of permission_resource_rel
-- ----------------------------
INSERT INTO `permission_resource_rel` VALUES ('1', '1', '1', '0', '0', '2020-12-27 18:50:50', '2020-12-27 18:50:50');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
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

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', null, '1', '请大家在后台不要对 \"管理员\" 做增删改的操作', '<p>a</p>', null, null, null, '0', '0', '8', '0', '0', '0', '0', '2020-02-04 18:42:03', null, '1000', '1000', null);
INSERT INTO `post` VALUES ('17015b5b06550b63e875cd04c58acb06', null, '1', '12月你好', '<p>a</p>', null, null, null, '0', '0', '17', '0', '0', '0', '0', '2020-02-05 22:17:05', null, '1000', '1000', null);
INSERT INTO `post` VALUES ('2', null, '1', '登陆后台有时候会报错，试试这个方法', '<p>a</p>', null, null, null, '0', '0', '2', '0', '0', '0', '0', '2020-02-05 14:26:20', null, '1000', '1000', null);

-- ----------------------------
-- Table structure for `reply_bak`
-- ----------------------------
DROP TABLE IF EXISTS `reply_bak`;
CREATE TABLE `reply_bak` (
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

-- ----------------------------
-- Records of reply_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `resource_name` varchar(50) NOT NULL COMMENT '资源名称',
  `resource_value` varchar(256) NOT NULL COMMENT '资源值',
  `resource_desc` varchar(256) NOT NULL COMMENT '资源描述',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`resource_id`),
  KEY `idx_module_id` (`module_id`),
  KEY `idx_resource_value` (`resource_value`(191))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '1', '首页', '/', '', '0', '0', '2020-12-27 18:50:04', '2020-12-27 18:50:04');

-- ----------------------------
-- Table structure for `resource_bak`
-- ----------------------------
DROP TABLE IF EXISTS `resource_bak`;
CREATE TABLE `resource_bak` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of resource_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_category`
-- ----------------------------
DROP TABLE IF EXISTS `resource_category`;
CREATE TABLE `resource_category` (
  `resource_category_id` varchar(36) NOT NULL COMMENT '资源类别ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `resource_category_name` varchar(50) NOT NULL COMMENT '资源类别名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`resource_category_id`),
  KEY `key_resource_category_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源类别表';

-- ----------------------------
-- Records of resource_category
-- ----------------------------

-- ----------------------------
-- Table structure for `resource_type`
-- ----------------------------
DROP TABLE IF EXISTS `resource_type`;
CREATE TABLE `resource_type` (
  `resource_type_id` varchar(36) NOT NULL COMMENT '资源类型ID',
  `resource_type_code` int(11) DEFAULT NULL COMMENT '资源类型编码',
  `resource_type_name` varchar(50) NOT NULL COMMENT '资源类型名称',
  PRIMARY KEY (`resource_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源类型表';

-- ----------------------------
-- Records of resource_type
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_desc` varchar(256) NOT NULL COMMENT '角色描述',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_name` (`role_name`) USING BTREE,
  KEY `idx_module_id` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_bak`
-- ----------------------------
DROP TABLE IF EXISTS `role_bak`;
CREATE TABLE `role_bak` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of role_bak
-- ----------------------------
INSERT INTO `role_bak` VALUES ('1', '超级管理员', '2019-02-26 11:25:48', '2019-03-04 22:25:42');

-- ----------------------------
-- Table structure for `role_permission_rel`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel` (
  `role_permission_rel_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_permission_rel_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission_rel_bak`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel_bak`;
CREATE TABLE `role_permission_rel_bak` (
  `role_permission_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_permission_rel_id`),
  KEY `key_role_permission_rel_role_id` (`role_id`) USING BTREE,
  KEY `key_role_permission_rel_permission_id` (`permission_id`) USING BTREE,
  CONSTRAINT `fk_role_permission_rel_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission_bak` (`permission_id`),
  CONSTRAINT `fk_role_permission_rel_role_id` FOREIGN KEY (`role_id`) REFERENCES `role_bak` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限关系表';

-- ----------------------------
-- Records of role_permission_rel_bak
-- ----------------------------
INSERT INTO `role_permission_rel_bak` VALUES ('1', '1', '13', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('2', '1', '14', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('3', '1', '15', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('4', '1', '16', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('5', '1', '17', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('6', '1', '18', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('7', '1', '19', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('8', '1', '20', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('9', '1', '21', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('10', '1', '22', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('11', '1', '23', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('12', '1', '24', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('13', '1', '25', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('14', '1', '26', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('15', '1', '27', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('16', '1', '28', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('17', '1', '29', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('18', '1', '30', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('19', '1', '31', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('20', '1', '32', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('21', '1', '33', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('22', '1', '34', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('23', '1', '35', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('24', '1', '47', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('25', '1', '36', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('26', '1', '37', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('27', '1', '38', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('28', '1', '39', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('29', '1', '40', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('30', '1', '41', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('31', '1', '42', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('32', '1', '43', '2019-03-11 21:54:02', '2019-03-11 21:54:02');
INSERT INTO `role_permission_rel_bak` VALUES ('33', '1', '44', '2019-03-11 21:54:02', '2019-03-11 21:54:02');

-- ----------------------------
-- Table structure for `role_user_rel`
-- ----------------------------
DROP TABLE IF EXISTS `role_user_rel`;
CREATE TABLE `role_user_rel` (
  `role_user_rel_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_user_rel_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关联表';

-- ----------------------------
-- Records of role_user_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `sidebar`
-- ----------------------------
DROP TABLE IF EXISTS `sidebar`;
CREATE TABLE `sidebar` (
  `sidebar_id` varchar(36) NOT NULL COMMENT '侧边栏ID',
  `parent_sidebar_id` varchar(36) DEFAULT NULL COMMENT '父级侧边栏ID',
  `permission_id` varchar(36) DEFAULT NULL COMMENT '权限ID',
  `user_id` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `sidebar_name` varchar(50) NOT NULL COMMENT '侧边栏的名字',
  `sidebar_url` varchar(500) DEFAULT NULL COMMENT '点击侧边栏时发送的请求URL',
  `sidebar_icon` varchar(500) DEFAULT NULL,
  `sidebar_sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sidebar_id`),
  KEY `key_sidebar_parent_sidebar_id` (`parent_sidebar_id`),
  KEY `key_sidebar_permission_id` (`permission_id`),
  KEY `key_sidebar_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='侧边栏表';

-- ----------------------------
-- Records of sidebar
-- ----------------------------
INSERT INTO `sidebar` VALUES ('170af11451b0b64b11aafc1412b83134', '', null, null, '角色管理', '#', null, '1', '2020-03-06 16:58:29', null);
INSERT INTO `sidebar` VALUES ('170af21de69ebd3c1a57e4041d88f6bd', '170af11451b0b64b11aafc1412b83134', null, null, '角色列表', '/admin/role/list', null, '2', '2020-03-06 17:16:36', null);
INSERT INTO `sidebar` VALUES ('170af2316544cd93c02259944e09b8d0', '170af11451b0b64b11aafc1412b83134', null, null, '角色添加', '/admin/role/add', null, '3', '2020-03-06 17:17:57', null);
INSERT INTO `sidebar` VALUES ('170af244a0d8217e9e2ab124ac48a13f', '', null, null, '权限管理', '#', null, '4', '2020-03-06 17:19:15', null);
INSERT INTO `sidebar` VALUES ('170af24dbff7a0a83656d5d4080aa60f', '170af244a0d8217e9e2ab124ac48a13f', null, null, '权限列表', '/admin/permission/list', null, '5', '2020-03-06 17:19:53', null);
INSERT INTO `sidebar` VALUES ('170af254ac359684ac30cb54aaebbfff', '170af244a0d8217e9e2ab124ac48a13f', null, null, '权限添加', '/admin/permission/add', null, '6', '2020-03-06 17:20:21', null);

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `system_id` bigint(20) NOT NULL COMMENT '系统ID',
  `system_name` varchar(256) NOT NULL COMMENT '系统名称',
  `system_code` varchar(50) NOT NULL COMMENT '系统名称',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用（0：启用，1：禁用）',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：有效，1：删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统表';

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES ('1', '认证系统', 'authentication', '0', '0', '2020-12-22 20:41:44', '2020-12-22 20:41:44');
INSERT INTO `system` VALUES ('2', '授权系统', 'authorization', '0', '0', '2020-12-22 20:41:44', '2020-12-22 20:41:44');
INSERT INTO `system` VALUES ('3', '权限系统', 'RBAC', '0', '0', '2020-12-22 20:41:44', '2020-12-22 20:41:44');
INSERT INTO `system` VALUES ('4', '用户系统', 'user', '0', '0', '2020-12-22 20:41:44', '2020-12-22 20:41:44');
INSERT INTO `system` VALUES ('5', '业务系统', 'biz', '0', '0', '2020-12-22 20:41:44', '2020-12-22 20:41:44');

-- ----------------------------
-- Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
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

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('1', null, null, '基础配置', '0', '', null, '0', '0');
INSERT INTO `system_config` VALUES ('2', null, null, '上传配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('3', null, null, '邮箱配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('4', null, null, '积分配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('5', null, null, 'Redis配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('6', null, null, 'Elasticsearch配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('7', null, null, 'Github登录配置，<a href=\"https://github.com/settings/developers\" target=\"_blank\">申请地址</a>', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('8', null, null, 'WebSocket配置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('9', 'base_url', 'http://localhost:8080', '网站部署后访问的域名，注意这个后面没有 \"/\"', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('10', 'site_name', 'Roothub', '站点名称', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('11', 'site_intro', 'Java论坛', '站点介绍', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('12', 'cookie_name', 'user', 'cookie名称', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('13', 'cookie_max_age', '2592000', 'cookie存活的最大时间', '1', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('14', 'cookie_domain', 'localhost', '存cookie时用到的域名，要与网站部署后访问的域名一致', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('15', 'cookie_path', '/', 'cookie的路径', '1', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('16', 'page_size', '50', '分页每页条数', '1', 'number', null, '0', '1');
INSERT INTO `system_config` VALUES ('17', 'local_upload_filedir', 'file:F:/upload/', '自定义文件保存路径，注意这个以 \"file:\" 开头，后面有 \"/\"（这种方式可以将资源存储在服务器的任意目录里，前提是要有该目录的读写权限）', '30', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('18', 'local_upload_user_filedir', 'file:F:/upload/roothub/user/', '自定义用户文件保存路径，注意这个后面有 \"/\"并且保证有该目录的读权限以', '30', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('19', 'local_upload_node_filedir', 'file:F:/upload/roothub/node/', '自定义节点文件保存路径，注意这个后面有 \"/\"并且保证有该目录的读权限', '30', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('20', 'local_upload_tag_filedir', 'file:F:/upload/roothub/tag/', '自定义标签文件保存路径，注意这个后面有 \"/\"并且保证有该目录的读权限', '30', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('21', 'redis_host', '127.0.0.1', 'redis服务地址', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('22', 'redis_port', '6379', 'redis服务端口（默认: 6379）', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('23', 'redis_password', '', 'redis服务密码（没有的话可以不用填）', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('24', 'redis_timeout', '2000', '网站连接redis服务超时时间，单位毫秒', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('25', 'redis_max_idle', '20', '连接池最多空闲实例', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('26', 'redis_max_total', '50', '连接池最多创建实例', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('27', 'redis_database', '0', '网站连接redis服务的哪个数据库，默认 0 号数据库，取值范围 0-15', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('28', 'redis_ssl', '0', 'redis服务是否开启认证连接（0：否，1：是）', '5', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('29', 'default_upload', '0', '默认上传', '2', 'radio', null, '0', '0');
INSERT INTO `system_config` VALUES ('30', 'local_upload', '0', '自定义上传', '2', 'radio', null, '0', '0');
INSERT INTO `system_config` VALUES ('31', 'static_url', '/static/**', '静态文件访问URL（默认上传和自定义上传的URL最好设置成一样的，否则更换上传方式后会导致之前的资源访问不了），注意最后有个\"/**\"', '30', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('32', 'accessKeyId', '', 'AccessKeyId（强烈推荐这种方式）', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('33', 'accessKeySecret', '', 'AccessKeySecret', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('34', 'endpoint', 'http://oss-cn-shenzhen.aliyuncs.com', 'Endpoint', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('35', 'bucketName', 'roothub', 'BucketName', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('36', 'oss_filedir', 'images/', '阿里云OSS静态文件存储路径，注意前面没有\"/\"，后面有 \"/\"', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('37', 'oss_static_url', ' https://roothub.oss-cn-shenzhen.aliyuncs.com/', '阿里云OSS静态文件访问的URL，注意这个后面有 \"/\"', '45', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('38', 'open_redis', '1', '是否开启Redis', '5', 'radio', null, '0', '0');
INSERT INTO `system_config` VALUES ('39', 'create_topic_score', '10', '创建话题奖励的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('40', 'create_reply_score', '5', '发布评论奖励的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('41', 'delete_topic_score', '10', '删除话题要被扣除的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('42', 'delete_reply_score', '5', '删除评论要被扣除的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('43', 'up_topic_score', '3', '点赞话题奖励话题作者的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('44', 'up_reply_score', '3', '点赞评论奖励评论作者的积分', '4', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('45', 'oss_upload', '1', '阿里云OSS', '2', 'radio', null, '0', '0');
INSERT INTO `system_config` VALUES ('46', 'default_upload_filedir', '/upload/', '默认的文件保存路径，注意这个后面有 \"/\"（这种方式会将资源存储在war包里，不推荐这种方式，因为重新部署应用时会初始化）', '29', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('47', 'static_url', '/static/**', '静态文件访问URL（默认上传和自定义上传的URL最好设置成一样的，否则更换上传方式后会导致之前的资源访问不了），注意最后有个\"/**\"', '29', 'text', null, '0', '0');
INSERT INTO `system_config` VALUES ('48', null, null, '分页设置', '0', null, null, '0', '0');
INSERT INTO `system_config` VALUES ('49', 'index_page_size', '50', '首页', '48', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('50', 'node_page_size', '50', '节点页', '48', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('51', 'tag_page_size', '50', '标签页', '48', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('52', 'user_page_size', '50', '用户主页', '48', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('53', 'search_page_size', '50', '搜索页', '48', 'number', null, '0', '0');
INSERT INTO `system_config` VALUES ('54', 'default_upload_node_filedir', '/upload/node/', '默认的节点文件保存路径（保存在war包里）', '29', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('55', 'default_upload_tag_filedir', '/upload/tag/', '默认的标签文件保存路径（保存在war包里）', '29', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('56', 'default_upload_user_filedir', '/upload/user/', '默认的用户文件保存路径（保存在war包里）', '29', 'text', null, '0', '1');
INSERT INTO `system_config` VALUES ('57', 'upload_type', '45', '上传类型', '2', 'hidden', null, '0', '0');

-- ----------------------------
-- Table structure for `tab`
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `tab_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `tab_desc` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0：否 1：是',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `tab_order` int(2) DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='父板块表';

-- ----------------------------
-- Records of tab
-- ----------------------------
INSERT INTO `tab` VALUES ('1', 'all', '全部', '0', '2018-07-15 15:59:48', '1');
INSERT INTO `tab` VALUES ('2', 'hot', '最热', '0', '2018-07-15 16:00:49', '2');
INSERT INTO `tab` VALUES ('3', 'new', '最新', '0', '2019-01-03 20:19:41', '3');
INSERT INTO `tab` VALUES ('4', 'lonely', '无人问津', '0', '2019-01-03 20:20:49', '4');
INSERT INTO `tab` VALUES ('5', 'member', '关注', '0', '2018-07-15 20:49:50', '15');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_name` varchar(25) CHARACTER SET utf8 NOT NULL COMMENT '标签名称',
  `tag_state` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签状态',
  `tag_avatar` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签图标',
  `tag_content` varchar(250) CHARACTER SET utf8 DEFAULT NULL COMMENT '标签简介',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '美国', '1000', '/tag/avatar', '美利坚', '2020-01-05 12:51:44', '2020-01-05 12:52:47');
INSERT INTO `tag` VALUES ('2', '中国', '1000', null, '中华人民', '2020-01-06 12:51:44', '2020-01-06 12:52:47');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `ptab` varchar(50) DEFAULT NULL,
  `tab` varchar(50) DEFAULT NULL,
  `title` varchar(250) NOT NULL,
  `tag` varchar(250) DEFAULT NULL,
  `content` longtext,
  `excerpt` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `last_reply_time` datetime DEFAULT NULL,
  `last_reply_author` varchar(50) DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  `author` varchar(50) DEFAULT NULL,
  `top` tinyint(1) DEFAULT '0',
  `good` tinyint(1) DEFAULT '0',
  `show_status` tinyint(1) DEFAULT NULL,
  `reply_count` int(11) NOT NULL DEFAULT '0',
  `is_delete` tinyint(1) DEFAULT '0',
  `tag_is_count` int(1) DEFAULT '0',
  `post_good_count` int(10) DEFAULT '0',
  `post_bad_count` int(10) DEFAULT '0',
  `status_cd` varchar(4) DEFAULT NULL,
  `node_slug` varchar(50) DEFAULT NULL,
  `node_title` varchar(50) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `avatar` varchar(250) DEFAULT NULL,
  `url` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `key_tab16` (`tab`),
  KEY `key_author17` (`author`),
  KEY `key_node_title18` (`node_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of topic
-- ----------------------------

-- ----------------------------
-- Table structure for `topic_bak`
-- ----------------------------
DROP TABLE IF EXISTS `topic_bak`;
CREATE TABLE `topic_bak` (
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

-- ----------------------------
-- Records of topic_bak
-- ----------------------------

-- ----------------------------
-- Table structure for `up_down`
-- ----------------------------
DROP TABLE IF EXISTS `up_down`;
CREATE TABLE `up_down` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `up_down` tinyint(1) DEFAULT NULL COMMENT '0:down 1:up',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Records of up_down
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', null, null, '10', '/default/front/common/images/default-avatar.jpg', '1ad23ffffa@qq.com', null, '这家伙很懒，什么都没留下', null, '0', '2020-01-31 18:38:23', null, '0', '1474379391a14a6e88dc59ce8298251e', '1000', null, null, '2', null);

-- ----------------------------
-- Table structure for `user_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel` (
  `user_role_rel_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `role_id` varchar(36) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_rel_id`),
  KEY `key_user_role_rel_user_id` (`user_id`),
  KEY `key_user_role_rel_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联关系表';

-- ----------------------------
-- Records of user_role_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `visit`
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `visit_id` varchar(36) NOT NULL COMMENT '访问ID',
  `source_id` varchar(36) NOT NULL COMMENT '访问者ID',
  `target_id` varchar(36) NOT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`visit_id`),
  KEY `key_source_id` (`source_id`),
  KEY `key_target_id` (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问表';

-- ----------------------------
-- Records of visit
-- ----------------------------
