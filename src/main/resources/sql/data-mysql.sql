INSERT INTO `user` (user_id, user_name, password) VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70');

INSERT INTO `menu` VALUES (1, NULL, NULL, NULL, '角色管理', '#', NULL, 1, '2020-11-28 17:33:31', '2020-11-28 17:33:31');
INSERT INTO `menu` VALUES (2, 1, NULL, NULL, '角色列表', '/admin/role/list', NULL, 2, '2020-11-28 17:34:06', '2020-11-28 17:34:06');
INSERT INTO `menu` VALUES (3, 1, NULL, NULL, '角色添加', '/admin/role/add', NULL, 3, '2020-11-28 17:34:38', '2020-11-28 17:34:38');
INSERT INTO `menu` VALUES (4, NULL, NULL, NULL, '权限管理', '#', NULL, 4, '2020-11-28 17:34:50', '2020-11-28 17:34:55');
INSERT INTO `menu` VALUES (5, 4, NULL, NULL, '权限列表', '/admin/permission/list', NULL, 5, '2020-11-28 17:35:51', '2020-11-28 17:35:51');
INSERT INTO `menu` VALUES (6, 4, NULL, NULL, '权限添加', '/admin/permission/add', NULL, 6, '2020-11-28 17:36:19', '2020-11-28 17:36:19');