/*
Navicat MySQL Data Transfer

Source Server         : aliyun-120.79.94.184
Source Server Version : 50722
Source Host           : 120.79.94.184:3306
Source Database       : roothub

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-14 10:35:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `tid` int(11) DEFAULT NULL COMMENT '主题ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------


-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '关注者ID',
  `fid` int(11) DEFAULT NULL COMMENT '被关注者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

-- ----------------------------
-- Records of follow
-- ----------------------------


-- ----------------------------
-- Table structure for `root_notice`
-- ----------------------------
DROP TABLE IF EXISTS `root_notice`;
CREATE TABLE `root_notice` (
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

-- ----------------------------
-- Records of root_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `root_reply`
-- ----------------------------
DROP TABLE IF EXISTS `root_reply`;
CREATE TABLE `root_reply` (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复标识',
  `topic_id` int(11) DEFAULT NULL COMMENT '话题id',
  `topic_author_id` int(11) DEFAULT NULL COMMENT '话题作者id',
  `reply_content` longtext COMMENT '回复内容',
  `create_date` datetime DEFAULT NULL COMMENT '回复时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `reply_author_id` int(11) DEFAULT NULL COMMENT '当前回复用户id',
  `reply_author_name` varchar(50) DEFAULT NULL COMMENT '当前回复用户昵称',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0:默认 1:删除',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读 0:默认 1:未读',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否可见 0:默认 1:不可见',
  `reply_good_count` int(10) NOT NULL DEFAULT '0' COMMENT '点赞',
  `reply_bad_count` int(10) NOT NULL DEFAULT '0' COMMENT '踩数',
  `reply_type` varchar(16) NOT NULL DEFAULT '' COMMENT '回复类型',
  `reply_read_count` int(11) DEFAULT NULL COMMENT '回复阅读数量',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '回复状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='回复表';

-- ----------------------------
-- Records of root_reply
-- ----------------------------

-- ----------------------------
-- Table structure for `root_section`
-- ----------------------------
DROP TABLE IF EXISTS `root_section`;
CREATE TABLE `root_section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(45) NOT NULL DEFAULT '' COMMENT '板块名称',
  `section_tab` varchar(45) NOT NULL DEFAULT '' COMMENT '板块标签',
  `section_desc` varchar(300) NOT NULL DEFAULT '' COMMENT '板块描述',
  `section_topic_num` int(10) NOT NULL DEFAULT '0' COMMENT '板块帖子数目',
  `show_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示，0:不显示 1:显示',
  `display_index` int(11) NOT NULL COMMENT '板块排序',
  `default_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认显示板块 0:默认 1:显示',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '模块父节点',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '板块状态 1000:有效 1100:无效 1200:未生效',
  PRIMARY KEY (`section_id`),
  UNIQUE KEY `tabunique` (`section_tab`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='板块表';

-- ----------------------------
-- Records of root_section
-- ----------------------------
INSERT INTO `root_section` VALUES ('2', '默认', 'all', '', '0', '1', '1', '1', '0', null, null, null);
INSERT INTO `root_section` VALUES ('3', '精华', 'good', '', '0', '1', '2', '1', '0', '2018-05-12 12:03:30', null, null);
INSERT INTO `root_section` VALUES ('4', '最新', 'newest', '', '0', '1', '3', '1', '0', '2018-05-12 14:47:35', null, null);
INSERT INTO `root_section` VALUES ('5', '等待评论', 'noReply', '', '0', '1', '4', '1', '0', '2018-05-12 14:55:30', null, null);

-- ----------------------------
-- Table structure for `root_topic`
-- ----------------------------
DROP TABLE IF EXISTS `root_topic`;
CREATE TABLE `root_topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `ptab` varchar(50) DEFAULT NULL COMMENT '父板块标识',
  `tab` varchar(50) DEFAULT NULL COMMENT '版块标识',
  `title` varchar(128) NOT NULL COMMENT '话题标题',
  `tag` varchar(255) DEFAULT NULL COMMENT '话题内容标签',
  `content` longtext COMMENT '话题内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `last_reply_time` datetime DEFAULT NULL COMMENT '最后回复话题时间，用于排序',
  `last_reply_author` varchar(50) DEFAULT '' COMMENT '最后回复话题的用户id',
  `view_count` int(11) DEFAULT NULL COMMENT '浏览量',
  `author` varchar(50) DEFAULT NULL COMMENT '话题作者id',
  `top` tinyint(1) DEFAULT NULL COMMENT '1置顶 0默认',
  `good` tinyint(1) DEFAULT NULL COMMENT '1精华 0默认',
  `show_status` tinyint(1) DEFAULT NULL COMMENT '1显示 0不显示',
  `reply_count` int(11) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '1删除 0默认',
  `tag_is_count` int(1) DEFAULT '0' COMMENT '话题内容标签是否被统计过 1是 0否默认',
  `post_good_count` int(10) DEFAULT '0' COMMENT '点赞',
  `post_bad_count` int(10) DEFAULT '0' COMMENT '踩数',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '话题状态 1000:有效 1100:无效 1200:未生效',
  `node_slug` varchar(50) DEFAULT NULL COMMENT '所属节点',
  `node_title` varchar(50) DEFAULT NULL COMMENT '节点名称',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `avatar` varchar(250) DEFAULT '69290780aaafb00aa37ff2a61342dded.png' COMMENT '话题作者头像',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='帖子表';

-- ----------------------------
-- Records of root_topic
-- ----------------------------

-- ----------------------------
-- Table structure for `root_user`
-- ----------------------------
DROP TABLE IF EXISTS `root_user`;
CREATE TABLE `root_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '用户密码',
  `user_sex` varchar(30) DEFAULT NULL COMMENT '用户性别',
  `user_addr` varchar(250) DEFAULT NULL COMMENT '用户地址',
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
  `third_access_token` varchar(50) DEFAULT NULL COMMENT '第三方登录获取的access_token',
  `status_cd` varchar(4) DEFAULT NULL COMMENT '用户状态 1000:有效 1100:无效 1200:未生效',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '用户登入ip',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '用户最后登入ip',
  `user_type` int(2) NOT NULL DEFAULT '2' COMMENT '用户类型 0:超级管理员 1:版主 2:普通用户',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `NICKNAME_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of root_user
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COMMENT='父板块表';

-- ----------------------------
-- Records of tab
-- ----------------------------
INSERT INTO `tab` VALUES ('1', 'all', '全部', '0', '2018-07-15 15:59:48', '1');
INSERT INTO `tab` VALUES ('2', 'hot', '最热', '0', '2018-07-15 16:00:49', '2');
INSERT INTO `tab` VALUES ('3', 'pl', '编程语言', '0', '2018-07-15 16:01:35', '3');
INSERT INTO `tab` VALUES ('4', 'db', '数据库', '0', '2018-07-15 16:02:37', '4');
INSERT INTO `tab` VALUES ('5', 'fe', '前端', '0', '2018-07-15 16:04:52', '5');
INSERT INTO `tab` VALUES ('6', 'play', '有趣', '0', '2018-07-15 16:05:39', '6');
INSERT INTO `tab` VALUES ('7', 'creative', '创意', '0', '2018-07-15 20:46:33', '7');
INSERT INTO `tab` VALUES ('8', 'host', '主机', '0', '2018-07-15 20:47:11', '8');
INSERT INTO `tab` VALUES ('9', 'dn', '域名', '0', '2018-07-15 20:47:46', '9');
INSERT INTO `tab` VALUES ('10', 'blog', '博客', '0', '2018-07-15 20:48:19', '10');
INSERT INTO `tab` VALUES ('11', 'tea', '下午茶馆', '0', '2018-07-15 20:48:53', '11');
INSERT INTO `tab` VALUES ('12', 'news', '资讯', '0', '2018-07-15 20:49:50', '12');
INSERT INTO `tab` VALUES ('13', 'qna', '提问', '0', '2018-07-15 20:50:06', '13');

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Records of up_down
-- ----------------------------

-- ----------------------------
-- Table structure for `visit`
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '访问者ID',
  `vid` int(11) DEFAULT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='访问记录表';

-- ----------------------------
-- Records of visit
-- ----------------------------