/*
Navicat MySQL Data Transfer

Source Server         : sen
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : root

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-07-04 11:56:57
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('52', '1', '1', '2018-06-30 17:35:54');
INSERT INTO `collect` VALUES ('53', '1', '52', '2018-06-30 19:00:20');
INSERT INTO `collect` VALUES ('55', '1', '51', '2018-06-30 19:04:24');
INSERT INTO `collect` VALUES ('59', '2', '1', '2018-06-30 20:52:26');
INSERT INTO `collect` VALUES ('60', '2', '52', '2018-06-30 20:52:55');
INSERT INTO `collect` VALUES ('61', '2', '51', '2018-06-30 20:53:11');
INSERT INTO `collect` VALUES ('62', '3', '1', '2018-07-01 20:37:45');
INSERT INTO `collect` VALUES ('63', '1', '57', '2018-07-02 14:03:00');
INSERT INTO `collect` VALUES ('64', '1', '32', '2018-07-02 15:00:06');
INSERT INTO `collect` VALUES ('65', '46', '66', '2018-07-03 17:23:55');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('3', '3', '1', '2018-07-01 21:35:52');
INSERT INTO `follow` VALUES ('4', '4', '1', '2018-07-01 21:38:01');
INSERT INTO `follow` VALUES ('5', '5', '1', '2018-07-01 21:39:38');
INSERT INTO `follow` VALUES ('6', '1', '5', '2018-07-01 22:21:47');
INSERT INTO `follow` VALUES ('17', '1', '3', '2018-07-01 22:51:14');
INSERT INTO `follow` VALUES ('23', '1', '2', '2018-07-02 13:17:59');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息通知表';

-- ----------------------------
-- Records of root_notice
-- ----------------------------
INSERT INTO `root_notice` VALUES ('1', null, '1', null, 'void', null, 'public', '2018-06-01 01:35:45', '2018-06-01 01:35:46', '', '1', null, null, '<p>zcccc</p>', '1000');
INSERT INTO `root_notice` VALUES ('2', null, '1', '3', 'void', null, 'public', '2018-06-19 10:13:36', '2018-06-19 10:13:36', '', '1', null, null, '<p>测试</p>', '1000');
INSERT INTO `root_notice` VALUES ('3', null, '1', '3', 'void', null, 'public', '2018-06-19 10:13:51', '2018-06-19 10:13:51', '', '1', null, null, '<p>ceshi</p>', '1000');
INSERT INTO `root_notice` VALUES ('4', null, '1', '3', 'void', null, 'public', '2018-06-19 10:14:13', '2018-06-19 10:14:13', '', '36', null, null, '<p>测试</p>', '1000');
INSERT INTO `root_notice` VALUES ('5', null, '1', '3', 'void', null, 'public', '2018-06-19 10:14:23', '2018-06-19 10:14:23', '', '36', null, null, '<p>测试2</p>', '1000');
INSERT INTO `root_notice` VALUES ('6', null, '1', '2', 'private', null, 'public', '2018-07-01 15:03:02', '2018-07-01 15:03:02', '', '32', null, null, '<p>测试</p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '1000');
INSERT INTO `root_notice` VALUES ('7', null, '1', '1', 'public', null, 'private', '2018-07-02 21:37:51', '2018-07-02 21:37:51', 'reply', '53', null, null, '<p>哼哼哼</p>', '1000');
INSERT INTO `root_notice` VALUES ('8', null, '1', '2', 'private', null, 'public', '2018-07-02 21:38:54', '2018-07-02 21:38:54', 'reply', '63', null, null, '<p>哈哈哈哈哈哈哈</p>', '1000');

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='回复表';

-- ----------------------------
-- Records of root_reply
-- ----------------------------
INSERT INTO `root_reply` VALUES ('6', '49', null, '测试回复内容01', '2018-05-26 00:30:36', '2018-05-26 00:30:36', null, 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('7', '49', null, '测试回复内容02', '2018-05-26 00:32:55', '2018-05-26 00:32:55', null, 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('8', '49', null, '测试回复内容03', '2018-06-01 00:42:32', '2018-06-01 00:42:32', null, 'private', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('9', '49', null, '测试回复内容04', '2018-06-01 01:06:15', '2018-06-01 01:06:15', null, 'private', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('10', '1', null, '<h1>zwz</h1>', '2018-06-01 01:24:16', '2018-06-01 01:24:16', null, 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('11', '1', null, '<h2>cs</h2>', '2018-06-01 01:27:48', '2018-06-01 01:27:48', null, 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('12', '1', null, '<p>zcccc</p>', '2018-06-01 01:35:27', '2018-06-01 01:35:27', null, 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('13', '1', null, '<p>测试</p>', '2018-06-02 21:51:10', '2018-06-02 21:51:11', null, 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('14', '1', null, '<p>测试啊</p>', '2018-06-02 21:55:36', '2018-06-02 21:55:36', null, 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('15', '1', null, '<p>测试1</p>', '2018-06-02 21:56:51', '2018-06-02 21:56:51', null, 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('16', '1', null, '<p>测试2</p>', '2018-06-02 21:58:48', '2018-06-02 21:58:48', null, 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('17', '1', null, '<p>测试</p>', '2018-06-19 10:13:35', '2018-06-19 10:13:35', '3', 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('18', '1', null, '<p>ceshi</p>', '2018-06-19 10:13:50', '2018-06-19 10:13:50', '3', 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('19', '36', null, '<p>测试</p>', '2018-06-19 10:14:12', '2018-06-19 10:14:12', '3', 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('20', '36', null, '<p>测试2</p>', '2018-06-19 10:14:22', '2018-06-19 10:14:22', '3', 'void', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('21', '52', null, '<p>测试</p>', '2018-06-20 09:40:50', '2018-06-20 09:40:50', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('22', '53', null, '<p>测试0630</p>', '2018-06-30 20:58:09', '2018-06-30 20:58:09', '2', 'private', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('23', '32', null, '<p>测试</p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '2018-07-01 15:03:02', '2018-07-01 15:03:02', '2', 'private', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('24', '54', null, '<p>测试</p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '2018-07-01 17:23:26', '2018-07-01 17:23:26', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('25', '54', null, '<p>测试时</p><p><img src=\"/resources/images/8d5494eef01f3a297523dde79825bc315c607c2b.gif\" style=\"max-width:100%;\"></p>', '2018-07-01 17:30:03', '2018-07-01 17:30:03', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('26', '54', null, '<p><img src=\"/resources/images/7a899e510fb30f24430f025ec395d143ac4b03d1.jpg\" style=\"max-width:100%;\"></p><p>1</p>', '2018-07-01 17:30:31', '2018-07-01 17:30:31', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('27', '54', null, '<p><img src=\"/resources/images/3cf09258d109b3dedb5a3b4dc0bf6c81810a4c68.jpg\" style=\"max-width:100%;\"></p><p>1</p>', '2018-07-01 17:31:46', '2018-07-01 17:31:46', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('28', '1', null, '<p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p><p>1</p>', '2018-07-02 15:32:33', '2018-07-02 15:32:33', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('29', '1', null, '<p>测试</p>', '2018-07-02 15:39:01', '2018-07-02 15:39:01', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('30', '1', null, '<p>穿的是</p>', '2018-07-02 15:39:08', '2018-07-02 15:39:08', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('31', '1', null, '<p>cs</p>', '2018-07-02 15:39:20', '2018-07-02 15:39:20', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('32', '1', null, '<p>测试</p>', '2018-07-02 15:39:24', '2018-07-02 15:39:24', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('33', '1', null, '<p>测试</p>', '2018-07-02 15:39:42', '2018-07-02 15:39:42', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('34', '62', null, '<p><span style=\"font-weight: bold;\">哈哈</span></p>', '2018-07-02 21:17:17', '2018-07-02 21:17:17', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('35', '53', null, '<p>哼哼哼</p>', '2018-07-02 21:37:50', '2018-07-02 21:37:50', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('36', '63', null, '<p>哈哈哈哈哈哈哈</p>', '2018-07-02 21:38:54', '2018-07-02 21:38:54', '2', 'private', '0', '0', '0', '0', '0', '', '0', '1000');
INSERT INTO `root_reply` VALUES ('37', '60', null, '<p>哈哈</p>', '2018-07-03 15:35:06', '2018-07-03 15:35:06', '1', 'public', '0', '0', '0', '0', '0', '', '0', '1000');

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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='帖子表';

-- ----------------------------
-- Records of root_topic
-- ----------------------------
INSERT INTO `root_topic` VALUES ('1', 'all', 'select', 'delete', '哈哈哈哈哈哈哈', '2018-05-07 23:02:01', '2018-05-07 23:02:01', '2018-07-02 15:39:42', 'public', '328', 'public', '1', '0', '1', '115', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('2', 'all', '测试发布话题', 'delete', '哈哈哈哈哈哈哈', '2018-05-08 00:36:05', '2018-05-08 00:36:05', '2018-05-08 00:36:05', 'public', '22', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('3', 'all', '测试发布话题01', 'delete', '哈哈哈哈哈哈哈', '2018-05-09 16:30:40', '2018-05-09 16:30:40', '2018-05-09 16:30:40', 'public', '13', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('9', 'all', '测试发布话题06', 'delete', '哈哈哈哈哈哈哈', '2018-05-09 17:06:20', '2018-05-09 17:06:20', '2018-05-09 17:06:20', 'public', '10', 'public', null, '1', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('12', 'all', '测试发布话题08', 'delete', '哈哈哈哈哈哈哈', '2018-05-09 17:23:26', '2018-05-09 17:23:26', '2018-05-09 17:23:26', 'public', '10', 'public', null, null, '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('13', 'all', '测试发布话题09', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:17:54', '2018-05-23 20:17:54', '2018-05-23 20:17:54', 'public', '11', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('14', 'all', '测试发布话题10', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:18:06', '2018-05-23 20:18:06', '2018-05-23 20:18:06', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('15', 'all', '测试发布话题11', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:18:16', '2018-05-23 20:18:16', '2018-05-23 20:18:16', 'public', '20', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('16', 'all', '测试发布话题12', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:18:24', '2018-05-23 20:18:24', '2018-05-23 20:18:24', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('18', 'all', '测试发布话题13', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:18:50', '2018-05-23 20:18:50', '2018-05-23 20:18:50', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('19', 'all', '测试发布话题14', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:18:59', '2018-05-23 20:18:59', '2018-05-23 20:18:59', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('20', 'all', '测试发布话题15', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:19:14', '2018-05-23 20:19:14', '2018-05-23 20:19:14', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('21', 'all', '测试发布话题16', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:19:30', '2018-05-23 20:19:30', '2018-05-23 20:19:30', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('22', 'all', '测试发布话题17', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:19:48', '2018-05-23 20:19:48', '2018-05-23 20:19:48', 'public', '11', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('23', 'all', '测试发布话题18', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:19:59', '2018-05-23 20:19:59', '2018-05-23 20:19:59', 'public', '10', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('24', 'all', '测试发布话题19', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:20:15', '2018-05-23 20:20:15', '2018-05-23 20:20:15', 'public', '13', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('25', 'all', '测试发布话题20', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:20:32', '2018-05-23 20:20:32', '2018-05-23 20:20:32', 'public', '11', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('26', 'all', '测试发布话题21', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:39:15', '2018-05-23 20:39:15', '2018-05-23 20:39:15', 'public', '11', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('27', 'all', '测试发布话题22', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:39:37', '2018-05-23 20:39:37', '2018-05-23 20:39:37', 'public', '13', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('28', 'all', '测试发布话题23', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:40:04', '2018-05-23 20:40:04', '2018-05-23 20:40:04', 'public', '12', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('29', 'all', '测试发布话题24', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:40:14', '2018-05-23 20:40:14', '2018-05-23 20:40:14', 'public', '12', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('30', 'all', '测试发布话题25', 'delete', '哈哈哈哈哈哈哈', '2018-05-23 20:40:26', '2018-05-23 20:40:26', '2018-05-23 20:40:26', 'public', '74', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('31', 'all', '测试发布话题26', 'delete', '哈哈哈哈哈哈哈', '2018-05-25 16:00:28', '2018-05-25 16:00:28', '2018-05-25 16:00:28', 'public', '11', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('32', 'all', '测试发布话题27', '测试', '<h1>测试发布话题</h1><h2>测试发布话题</h2><h3>测试发布话题</h3><h4>测试发布话题</h4><h5>测试发布话题</h5><p>测试发布话题</p><p><br></p>', '2018-05-25 16:09:48', '2018-05-25 16:09:48', '2018-07-01 15:03:02', 'private', '44', 'public', '0', '0', '1', '1', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('36', 'all', '测试发布话题28', '测试', '<h1><p>测试发布话题28</p></h1>', '2018-05-25 16:17:17', '2018-05-25 16:17:17', '2018-06-19 10:14:23', 'void', '27', 'public', '0', '0', '1', '2', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('38', 'all', '测试发布话题29', 'delete', '哈哈哈哈哈哈哈', '2018-05-25 16:24:28', '2018-05-25 16:24:28', '2018-05-25 16:24:28', 'public', '20', 'public', null, '1', '1', '100', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('39', 'all', '测试发布话题30', '测试', '<h1><p>测试发布话题30</p></h1>', '2018-05-25 16:27:34', '2018-05-25 16:27:34', null, null, '10', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('40', 'all', '测试发布话题31', '测试', '<h1>测试发布话题31</h1>', '2018-05-25 16:33:33', '2018-05-25 16:33:33', null, null, '8', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('41', 'all', '测试发布话题32', '话题32', '<h1>测试发布话题32</h1>', '2018-05-25 16:44:50', '2018-05-25 16:44:50', null, null, '11', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('42', 'all', '测试发布话题33', '话题33', '<h2><p>测试发布话题33</p></h2>', '2018-05-25 16:47:39', '2018-05-25 16:47:39', null, null, '11', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('43', 'all', '测试发布话题34', '话题34', '<h1><p>测试发布话题34</p></h1>', '2018-05-25 16:49:06', '2018-05-25 16:49:06', null, null, '3', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('44', 'all', '测试发布话题35', '话题35', '<h1><p>测试发布话题35</p></h1>', '2018-05-25 16:55:45', '2018-05-25 16:55:45', null, null, '3', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('45', 'all', '测试发布话题36', '测试图片', '<h1><p>测试发布话题36</p><p><img src=\"localhost:8080/resources/images/1.jpg\" style=\"max-width:100%;\"></p></h1>', '2018-05-25 17:26:08', '2018-05-25 17:26:08', null, null, '4', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('46', 'all', '测试发布话题37', '测试图片', '<h1><p>测试发布话题37</p><p><span style=\"font-weight: normal;\"></span></p><img src=\"/resources/images/32.png\" style=\"max-width:100%;\"></h1><h2>测试图片</h2><p><br></p>', '2018-05-25 17:30:46', '2018-05-25 17:30:46', null, null, '7', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('48', 'all', '测试发布话题38', '测试', '<p>测试发布话题38</p>', '2018-05-25 18:56:01', '2018-05-25 18:56:01', null, null, '3', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('49', 'all', '测试发布话题39', '测试', '<h4>测试发布话题38</h4><p><img src=\"/resources/images/2934349b033b5bb549bbdfef3ad3d539b600bcb7.jpg\" style=\"max-width:100%;\"></p>', '2018-05-25 18:56:59', '2018-05-25 18:56:59', null, null, '25', 'public', '0', '0', '1', '2', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('50', 'all', '测试033', '033', '', '2018-06-01 00:33:34', '2018-06-01 00:33:34', null, null, '2', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('51', 'all', '测试发布话题的作者的头像', '头像', '<p>测试话题头像</p>', '2018-06-03 01:00:06', '2018-06-03 01:00:06', null, null, '10', 'private', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1.jpg');
INSERT INTO `root_topic` VALUES ('52', 'all', '测试63', '测试', '<p>测试<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"></p><h1>测试</h1><p><span style=\"font-style: italic;\">测试</span></p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '2018-06-20 09:40:15', '2018-06-20 09:40:15', '2018-06-20 09:40:52', 'public', '19', 'public', '0', '0', '1', '1', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('53', 'all', '测试0630', '测试0630', '<p>测试</p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '2018-06-30 20:57:38', '2018-06-30 20:57:38', '2018-07-02 21:37:51', 'public', '12', 'private', '0', '0', '1', '2', '0', '1', '0', '0', null, null, null, null, '1.jpg');
INSERT INTO `root_topic` VALUES ('54', 'all', '测试0701', '测试0701', '<p>测试</p><p><img src=\"/resources/images/04b81d950a7b02088720bbc86ed9f2d3562cc869.jpg\" style=\"max-width:100%;\"></p>', '2018-07-01 17:23:05', '2018-07-01 17:23:05', '2018-07-01 17:31:46', 'public', '10', 'public', '0', '0', '1', '4', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('55', 'all', 'void测试', 'void', '<p>1</p><p><img src=\"/resources/images/7da73f59f61e03e49d820458.jpg\" style=\"max-width:100%;\"></p>', '2018-07-01 17:33:20', '2018-07-01 17:33:20', null, null, '1', 'void', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');
INSERT INTO `root_topic` VALUES ('56', 'all', 'void测试', 'void', '<p>2</p><p><br></p>', '2018-07-01 17:34:03', '2018-07-01 17:34:03', null, null, '2', 'void', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');
INSERT INTO `root_topic` VALUES ('57', 'all', '0702', '0702', '<p><img src=\"https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/1hReHaqafafeA1hsAkdjuVuAglFiahIVkzU0NdiaAFibGIWx30tYN72QrBQY6oslHevd4qCc86N9aiaQmkhk8h9NoA/640?wx_fmt=jpeg\" style=\"max-width:100%;\"></p><p>1</p>', '2018-07-02 14:02:37', '2018-07-02 14:02:37', null, null, '4', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('58', 'all', '有哪些老鸟程序员知道而新手不知道的小技巧？', '幽默', '<p>程序不等于数据结构加算法，而等于搜索引擎加英语。\n\n</p>', '2018-07-02 21:10:43', '2018-07-02 21:10:43', null, null, '2', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('59', 'all', '中国梦和美国梦的区别是什么？', '幽默', '<p>中国梦.txt，美国梦.exe\n\n</p>', '2018-07-02 21:11:20', '2018-07-02 21:11:20', null, null, '2', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('60', 'all', '如何反驳「程序员离开电脑就是废物」这个观点？', '幽默', '<p>不不不，很多程序员在电脑前也是废物。\n\n</p>', '2018-07-02 21:13:43', '2018-07-02 21:13:43', '2018-07-03 15:35:06', 'public', '8', 'public', '0', '0', '1', '1', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('61', 'all', '为什么程序员无论到哪儿都喜欢背电脑包，哪怕里面没有装电脑？', '幽默', '<p>因为他们没有别的包。\n\n</p>', '2018-07-02 21:14:42', '2018-07-02 21:14:42', null, null, '1', 'public', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('62', 'all', '程序员都有哪些强迫症行为？', '幽默', '<p>乘电梯的时候看着电梯的按钮面板（UI）常常会开始思考电梯的调度算法，然后仔细一想好像状态挺多的，多个实例之间状态可以互相影响，还涉及到一些优先级、加速度、预判方面的东西，仔细想想其实挺复杂的，然后还没等到出电梯就放弃了。然后不知道哪次坐又会望着面板开始想。\n\n</p>', '2018-07-02 21:15:08', '2018-07-02 21:15:08', '2018-07-02 21:17:17', 'public', '8', 'public', '0', '0', '1', '1', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('63', 'all', '为什么圣斗士每次出招前都要大喊一声招式？', '幽默', '<p>函数要先声明，然后才能调用。\n\n</p>', '2018-07-02 21:17:47', '2018-07-02 21:17:47', '2018-07-02 21:38:54', 'private', '11', 'public', '0', '0', '1', '1', '0', '1', '0', '0', null, null, null, null, '1530510188338cHVibGljavatar.png');
INSERT INTO `root_topic` VALUES ('64', 'all', '大家写代码时喜欢用 Guard Clause 还是一条路径走到底？', 'Guard Clause', '<p>前两天 Code Review 的时候被指出了这个问题。</p><p>如题，是</p><pre><code>bool func() {\n  if (xxx) return false;\n  if (xxx) return true;\n  xxx;\n  return true;\n}\n</code></pre><p>还是</p><pre><code>bool func() {\n  if (xxx) {\n    if (xxx) return false;\n    else return true;\n  } else {\n    xxx;\n    return true;\n  }\n}\n</code></pre><p>好像前者一般比较清爽，缩进层次也少。我只看过王垠在他的博客里强烈支持过第二种。</p>', '2018-07-03 17:20:16', '2018-07-03 17:20:16', null, null, '1', 'Roothuba', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');
INSERT INTO `root_topic` VALUES ('65', 'all', '当年 1.6 亿美金估值的公司—— Digg 是如何被一句 Python 函数可变默参 毁掉的', 'Digg ', '<p><a href=\"https://lethain.com/digg-v4/\" rel=\"nofollow\">https://lethain.com/digg-v4/</a></p><p>太戏剧性了。画重点：</p><p>2011 年，Google 推出「 Panda 」 机制动摇了很多老的 SEO 手段，digg 流量被腰斩。推出 DiggV4 作战计划。经过紧张的开发发布，不过访客页面没问题，已登录用户打不开 MyNews 页面。开发不得不用临时手段把登录用户的默认页面改成 TopNews</p><p>MyNews 只能通过不断重启进程才能短暂修复。初期以为是 cassandra 的缓存击穿了 memcache，后来加紧用 redis 重写了，还是得几个小时重启一次</p><p>（折腾了一个月之后）</p><p>终于发现原因了：API 服务器是 tornado 写的名字叫 Bobtail。里面最常用的函数是：</p><p><code>def get_user_by_ids(ids=[])</code></p><p>然后这个 ids 就一直被&nbsp;<code>append</code>&nbsp;直到撑爆内存</p><p>所以这个 MyNews 功能也渐渐用的人少，因为没法定制化看新闻，后来，大家都不去 diggv4 而去 reddit 了。。</p><p>后来，digg 以 50w 美金被别人收了。。</p><p>作为这次 digg v4 事件的受害者，觉得太神奇了。。</p>', '2018-07-03 17:22:01', '2018-07-03 17:22:01', null, null, '1', 'Roothuba', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');
INSERT INTO `root_topic` VALUES ('66', 'all', '关闭微信朋友圈 2 个月,有感而发', '微信朋友圈', '<p>在设置中：</p><ol><li>关掉发现的小红点</li><li>全局关闭朋友圈</li><li>管理发现那个地方把所有的勾都关闭，只留下一个扫二维码图表</li></ol><p>说真的，治好了我 5 分种就刷一次朋友圈的强迫症。。神清气爽。。</p>', '2018-07-03 17:23:42', '2018-07-03 17:23:42', null, null, '3', 'Roothuba', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');
INSERT INTO `root_topic` VALUES ('67', 'all', '有没有人愿意交换个人博客友情链接的。。。', '博客', '<p>我的博客地址是：&nbsp;<a href=\"https://itfucking.com/\" rel=\"nofollow\">https://itfucking.com/</a></p><p>还没多少文章，不过最近准备开始持续输出了。</p><p>博客的文章 100%都是我自己原创的，虽然目前内容还不多，不过相信在一段时间的积累后也会变成一个不错的原创内容博客。</p><p>其实这个博客很多年前就建立了，只是一直没有用心经营，现在打算认真做起来。</p><p>在这种情况下，流量就是我需要考虑的一个问题了。</p><p>因此，想问问有没有跟我一样想法的朋友愿意交换个友链什么的。</p><p>愿意的朋友就在下面留言好啦。</p><p>或者给我邮件：daoye.more#<a href=\"http://outlook.com/\" rel=\"nofollow\">outlook.com</a>&nbsp;。</p>', '2018-07-03 17:25:35', '2018-07-03 17:25:35', null, null, '2', 'Roothuba', '0', '0', '1', '0', '0', '1', '0', '0', null, null, null, null, '69290780aaafb00aa37ff2a61342dded.png');

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of root_user
-- ----------------------------
INSERT INTO `root_user` VALUES ('1', 'public', '123456', null, '', null, '1530510188338cHVibGljavatar.png', '', '', '过去再美终究过去qaq', '', null, null, '2018-07-02 13:43:08', null, null, null, null, null, '2', null);
INSERT INTO `root_user` VALUES ('2', 'private', '123456', '男', '长沙', '100', '1.jpg', '123456@qq.com', 'www.baidu.com', '哈哈', '1158827539', '1', '2018-05-06 20:10:37', '2018-05-06 20:10:37', '1', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('3', 'void', '123456', null, '长沙', '100', '69290780aaafb00aa37ff2a61342dded.png', 'abc@q.acv', 'www.baidu.com', '哈哈哈哈', '1158827539', '0', '2018-05-06 20:12:20', '2018-05-06 20:12:20', '0', null, null, '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('5', 'throws', '123456', '男', '长沙', '100', '69290780aaafb00aa37ff2a61342dded.png', '123456@qq.com', 'www.baidu.com', '哈哈', '1158827539', '0', '2018-05-06 22:39:01', '2018-05-06 22:39:01', '0', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('6', 'Exception', '123456', '男', '长沙', '100', '69290780aaafb00aa37ff2a61342dded.png', '123456@qq.com', 'www.baidu.com', '哈哈', '1158827539', '0', '2018-05-06 22:44:04', '2018-05-06 22:44:04', '0', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('7', 'import', '123456', '男', '长沙', '100', '69290780aaafb00aa37ff2a61342dded.png', '123456@qq.com', 'www.baidu.com', '哈哈', '1158827539', '0', '2018-05-06 22:45:09', '2018-05-06 22:45:09', '0', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('8', 'fangjin', '000000', '女', '武汉', '10', '69290780aaafb00aa37ff2a61342dded.png', '12233444', '33333', '哈哈哈哈', '789', '1', '2018-05-06 23:01:12', '2018-05-07 21:38:38', '0', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('9', 'ruqin', '123456', '男', '长沙', '100', '69290780aaafb00aa37ff2a61342dded.png', '123456@qq.com', 'www.baidu.com', '哈哈', '1158827539', '0', '2018-05-09 16:57:21', '2018-05-09 16:57:21', '0', null, '1000', '127.0.1', '127.0.1', '0', null);
INSERT INTO `root_user` VALUES ('11', 'yiiu', '123', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '123456@123', null, null, null, '0', '2018-05-18 16:26:28', '2018-05-18 16:26:28', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('12', 'qwer', 'qwer', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '', null, null, null, '0', '2018-05-18 16:33:25', '2018-05-18 16:33:25', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('13', 'fff', 'gg', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', 'hhh@aa', null, null, null, '0', '2018-05-18 16:43:36', '2018-05-18 16:43:36', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('14', 'zhangsan', '123', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '1158827539@qq.com', null, null, null, '0', '2018-05-18 17:50:12', '2018-05-18 17:50:12', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('15', 'bbb', '1234', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '11588ddd27539@qq.com', null, null, null, '0', '2018-05-18 18:27:20', '2018-05-18 18:27:20', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('17', '1234', '5667', null, null, '0', null, '1234444@qq', null, null, null, '0', '2018-05-18 18:27:51', '2018-05-18 18:27:51', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('19', 'wangwu', '123456', null, null, '0', null, '555@8866', null, null, null, '0', '2018-05-18 18:28:40', '2018-05-18 18:28:40', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('21', 'zhaoliu', 'q97238', null, null, '0', null, '97238@qq.com', null, null, null, '0', '2018-05-18 18:31:15', '2018-05-18 18:31:15', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('23', 'sdf', '1234', null, null, '0', null, '3216@qq', null, null, null, '0', '2018-05-18 18:37:53', '2018-05-18 18:37:53', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('25', 'publiccc', '456', null, null, '0', null, '11588275aaaaa39@qq.com', null, null, null, '0', '2018-05-18 18:39:16', '2018-05-18 18:39:16', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('27', 'ioc', '123', null, null, '0', null, 'oracle@qq.com', null, null, null, '0', '2018-05-18 18:56:20', '2018-05-18 18:56:20', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('28', 'publicfff', '123', null, null, '0', null, '115882aaaa7539@qq.com', null, null, null, '0', '2018-05-18 19:08:44', '2018-05-18 19:08:44', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('29', 'publichhhhhh', '123', null, null, '0', null, '11588aaaa27539@qq.com', null, null, null, '0', '2018-05-18 19:18:53', '2018-05-18 19:18:53', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('30', 'publicuuuuuuuu', '123', null, null, '0', null, '1158827555539@qq.com', null, null, null, '0', '2018-05-18 19:30:56', '2018-05-18 19:30:56', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('31', 'qpublic', '123', null, null, '0', null, 'hhh@aaf', null, null, null, '0', '2018-05-18 19:33:39', '2018-05-18 19:33:39', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('32', 'wpublic', '123', null, null, '0', null, '555@886', null, null, null, '0', '2018-05-18 19:37:00', '2018-05-18 19:37:00', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('33', 'rpublic', '123', null, null, '0', null, '1158827539@qq.comc', null, null, null, '0', '2018-05-18 19:39:16', '2018-05-18 19:39:16', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('34', 'mybatiss', '123', null, null, '0', null, '555@884', null, null, null, '0', '2018-05-18 21:36:32', '2018-05-18 21:36:32', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('35', 'mybatisa', '123', null, null, '0', null, '123456@1234', null, null, null, '0', '2018-05-18 21:38:34', '2018-05-18 21:38:34', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('36', 'publid', '123', null, null, '0', null, '1158827539@qq.coma', null, null, null, '0', '2018-05-18 21:39:50', '2018-05-18 21:39:50', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('37', 'publidd', '123', null, null, '0', null, '1158827539@qq.comab', null, null, null, '0', '2018-05-18 21:43:13', '2018-05-18 21:43:13', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('38', '经济', '123', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '1158827539@qq.comj', null, null, null, '0', '2018-05-20 01:03:36', '2018-05-20 01:03:36', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('39', '素素素', '123', null, null, '0', null, '123', null, null, null, '0', '2018-06-19 09:55:07', '2018-06-19 09:55:07', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('40', '啊', 'a', null, null, '0', null, 'a', null, null, null, '0', '2018-06-20 01:09:57', '2018-06-20 01:09:57', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('41', 'a', '1', null, null, '0', null, '1', null, null, null, '0', '2018-06-20 02:05:24', '2018-06-20 02:05:24', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('42', 'aa', '11111111111111', null, null, '0', null, '12', null, null, null, '0', '2018-06-20 02:10:39', '2018-06-20 02:10:39', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('43', 'aab', '123456', null, null, '0', null, '115@qq.cn', null, null, null, '0', '2018-06-20 02:13:31', '2018-06-20 02:13:31', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('44', 'roothub', '123456', null, null, '0', null, '11588217539@qq.com', null, null, null, '0', '2018-07-03 15:40:19', '2018-07-03 15:40:19', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('45', 'oop', '123456', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', '1234444@qq.cn', null, null, null, '0', '2018-07-03 15:48:41', '2018-07-03 15:48:41', '0', null, '1000', null, null, '2', null);
INSERT INTO `root_user` VALUES ('46', 'Roothuba', '123456', null, null, '0', '69290780aaafb00aa37ff2a61342dded.png', 'sdsdhg@ww.com', null, '这家伙很懒，什么都没留下', null, '0', '2018-07-03 15:52:26', '2018-07-03 15:52:26', '0', null, '1000', null, null, '2', null);

-- ----------------------------
-- Table structure for `visit`
-- ----------------------------
DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '访问者ID',
  `vid` int(11) DEFAULT NULL COMMENT '被访问者ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问记录表';

-- ----------------------------
-- Records of visit
-- ----------------------------
