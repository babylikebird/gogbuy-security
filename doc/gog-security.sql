/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : gog-security

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-01-29 16:11:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('app', null, 'app', 'app', 'password,refresh_token', null, null, null, null, null, null);
INSERT INTO `oauth_client_details` VALUES ('webApp', null, 'webApp', 'app', 'authorization_code,password,refresh_token,client_credentials', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(48) NOT NULL,
  `parent_id` varchar(48) NOT NULL COMMENT '上级部门ID，一级部门为0',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `org_code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_dept_orgcode_un` (`org_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` varchar(48) NOT NULL,
  `role_id` varchar(48) DEFAULT NULL COMMENT '角色ID',
  `dept_id` varchar(48) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_element
-- ----------------------------
DROP TABLE IF EXISTS `sys_element`;
CREATE TABLE `sys_element` (
  `id` varchar(48) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '资源编码（必须唯一）',
  `type` int(11) DEFAULT '2' COMMENT '类型 2：按钮，3：uri',
  `name` varchar(255) DEFAULT NULL COMMENT '按钮或资源名称',
  `uri` varchar(255) DEFAULT NULL COMMENT '资源标识符',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方式（GET,POST,DELETE,PUT）',
  `menu_id` varchar(48) DEFAULT NULL COMMENT '在哪个菜单下',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '前端页面地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_element_code_unique` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_element
-- ----------------------------
INSERT INTO `sys_element` VALUES ('956485708698402816', 'user:update', '2', '更新', '/user/update', 'POST', '955996044740878336', '更新用户', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485708836814848', 'user:delete', '2', '删除', '/user/delete/*', 'POST', '955996044740878336', '删除用户', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485708950061056', 'user:save', '2', '保存用户', '/user/save', 'POST', '955996044740878336', '保存用户', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485709159776256', 'user:setRole', '2', '设置角色', '/user/setRole', 'POST', '955996044740878336', '设置用户角色', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485709369491456', 'user:resetPassword', '2', '重置密码', '/user/resetPassword', 'POST', '955996044740878336', '重置密码', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485709465960448', 'user:menu', '2', '用户菜单', '/user/menu/*', 'POST', '955996044740878336', '获取用户菜单', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485709591789568', 'user:setDept', '2', '设置部门', '/user/setDept', 'POST', '955996044740878336', '设置用户所在的部门', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956485709717618688', 'user:modifyPassword', '2', '修改密码', '/user/modifyPassword', 'POST', '955996044740878336', '修改密码', null, '2018-01-25 19:15:38', null);
INSERT INTO `sys_element` VALUES ('956506743900508160', 'dept:update', '2', '更新', '/dept/update', 'POST', '955996047009996800', '更新部门', null, '2018-01-25 20:39:13', null);
INSERT INTO `sys_element` VALUES ('956506744047308800', 'dept:delete', '2', '删除', '/dept/delete/*', 'POST', '955996047009996800', '删除', null, '2018-01-25 20:39:13', null);
INSERT INTO `sys_element` VALUES ('956506744164749312', 'dept:list', '2', '部门列表', '/dept/list', 'POST', '955996047009996800', '部门列表', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506744303161344', 'dept:save', '2', '新增', '/dept/save', 'POST', '955996047009996800', '新增', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506744517070848', 'dept:tree', '2', '部门列表', '/dept/tree', 'POST', '955996047009996800', '部门树形列表', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506744722591744', 'dept:setRole', '2', '设置部门角色', '/dept/setRole', 'POST', '955996047009996800', '设置部门角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506744814866432', 'dept:addRole', '2', '添加部门角色', '/dept/addRole', 'POST', '955996047009996800', '添加部门角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506744932306944', 'element:update', '2', '更新', '/element/update', 'POST', '955996046800281600', '更新', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745062330368', 'element:delete', '2', '删除', '/element/delete/*', 'POST', '955996046800281600', '删除', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745175576576', 'element:list', '2', '列表', '/element/list', 'POST', '955996046800281600', '列表', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745272045568', 'element:save', '2', '新增', '/element/save', 'POST', '955996046800281600', '新增', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745389486080', 'menu:update', '2', '更新', '/menu/update', 'POST', '955996046800281600', '更新菜单', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745477566464', 'menu:delete', '2', '列表', '/menu/delete/*', 'POST', '955996046800281600', '删除菜单', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745595006976', 'menu:list', '2', '列表', '/menu/list', 'POST', '955996046800281600', '获取菜单列表', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745687281664', 'menu:save', '2', '新增', '/menu/save', 'POST', '955996046800281600', '新增菜单', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745817305088', 'menu:tree', '2', '树形列表', '/menu/tree', 'POST', '955996046800281600', '获取菜单树形列表', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506745930551296', 'permission:setRoleElement', '2', '设置页面元素', '/permission/setRoleElement', 'POST', '955996046590566400', '设置角色页面权限', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746052186112', 'permission:setRoleMenu', '2', '设置菜单', '/permission/setRoleMenu', 'POST', '955996046590566400', '设置角色菜单权限', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746144460800', 'role:get', '2', '获取角色', '/role/*', 'GET', '955996046590566400', '获取角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746257707008', 'role:update', '2', '更新', '/role/update', 'POST', '955996046590566400', '更新角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746354176000', 'role:delete', '2', '删除', '/role/delete/*', 'POST', '955996046590566400', '删除角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746433867776', 'role:save', '2', '新增', '/role/save', 'POST', '955996046590566400', '新增角色', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956506746559696896', 'user:get', '2', '获取用户信息', '/user/*', 'GET', '955996044740878336', '获取用户信息', null, '2018-01-25 20:39:14', null);
INSERT INTO `sys_element` VALUES ('956694154454548480', 'menu:setElement', '2', '设置元素', '/menu/setElement', 'POST', '955996046800281600', '设置菜单元素', null, '2018-01-26 09:03:56', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(48) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单资源编码（必须唯一）',
  `uri` varchar(255) DEFAULT NULL COMMENT '资源标识符',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方式GET\\POST\\',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父级菜单',
  `type` int(255) DEFAULT NULL COMMENT '类型   0：目录   1：菜单 ',
  `order_num` int(11) DEFAULT '0' COMMENT '排序（1,2，，3）',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `level` int(11) DEFAULT NULL COMMENT '几级菜单',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '页面地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_menu_code_unique` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('955993501461671936', '系统设置', 'sys_manager', '/dashboard', null, null, '0', '0', '100', null, '0', null, '2018-01-24 10:39:47', null);
INSERT INTO `sys_menu` VALUES ('955996044740878336', '用户管理', 'sys_user_manager', '/user/list', 'POST', null, '955993501461671936', '1', '1', null, '2', null, '2018-01-24 10:49:53', null);
INSERT INTO `sys_menu` VALUES ('955996046590566400', '角色管理', 'sys_role_manager', '/role/list', 'POST', null, '955993501461671936', '1', '2', null, '2', null, '2018-01-24 10:49:54', null);
INSERT INTO `sys_menu` VALUES ('955996046800281600', '菜单管理', 'sys_menu_manager', '/menu/list', 'POST', null, '955993501461671936', '1', '3', null, '2', null, '2018-01-24 10:49:54', null);
INSERT INTO `sys_menu` VALUES ('955996047009996800', '部门管理', 'sys_dept_manager', '/dept/list', 'POST', null, '955993501461671936', '1', '4', null, '2', null, '2018-01-24 10:49:54', null);

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` varchar(48) NOT NULL,
  `role_id` varchar(48) DEFAULT NULL COMMENT '角色ID',
  `resource_id` varchar(48) DEFAULT NULL COMMENT '资源ID',
  `resource_type` varchar(255) DEFAULT NULL COMMENT '资源类型（菜单：direct，menu，页面元素：button，uri）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('955998939234267136', '954227154406969344', '955993501461671936', 'menu', null, '2018-01-24 11:01:23');
INSERT INTO `sys_privilege` VALUES ('955998939360096256', '954227154406969344', '955996044740878336', 'menu', null, '2018-01-24 11:01:23');
INSERT INTO `sys_privilege` VALUES ('955998939553034240', '954227154406969344', '955996046590566400', 'menu', null, '2018-01-24 11:01:23');
INSERT INTO `sys_privilege` VALUES ('955998939766943744', '954227154406969344', '955996046800281600', 'menu', null, '2018-01-24 11:01:23');
INSERT INTO `sys_privilege` VALUES ('955998939884384256', '954227154406969344', '955996047009996800', 'menu', null, '2018-01-24 11:01:24');
INSERT INTO `sys_privilege` VALUES ('957885034381221888', '954227154406969344', '956485708698402816', 'button', null, '2018-01-29 15:56:03');
INSERT INTO `sys_privilege` VALUES ('957885034494468096', '954227154406969344', '956485708836814848', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885034611908608', '954227154406969344', '956485708950061056', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885034699988992', '954227154406969344', '956485709159776256', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885034821623808', '954227154406969344', '956485709369491456', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885034913898496', '954227154406969344', '956485709465960448', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035031339008', '954227154406969344', '956485709591789568', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035123613696', '954227154406969344', '956485709717618688', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035241054208', '954227154406969344', '956506743900508160', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035333328896', '954227154406969344', '956506744047308800', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035450769408', '954227154406969344', '956506744164749312', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035543044096', '954227154406969344', '956506744303161344', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035656290304', '954227154406969344', '956506744517070848', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035748564992', '954227154406969344', '956506744722591744', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035866005504', '954227154406969344', '956506744814866432', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885035962474496', '954227154406969344', '956506744932306944', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036075720704', '954227154406969344', '956506745062330368', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036167995392', '954227154406969344', '956506745175576576', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036285435904', '954227154406969344', '956506745272045568', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036381904896', '954227154406969344', '956506745389486080', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036495151104', '954227154406969344', '956506745477566464', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036591620096', '954227154406969344', '956506745595006976', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036709060608', '954227154406969344', '956506745687281664', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036801335296', '954227154406969344', '956506745817305088', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885036914581504', '954227154406969344', '956506745930551296', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037011050496', '954227154406969344', '956506746052186112', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037120102400', '954227154406969344', '956506746144460800', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037220765696', '954227154406969344', '956506746257707008', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037338206208', '954227154406969344', '956506746354176000', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037438869504', '954227154406969344', '956506746433867776', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037547921408', '954227154406969344', '956506746559696896', 'button', null, '2018-01-29 15:56:04');
INSERT INTO `sys_privilege` VALUES ('957885037640196096', '954227154406969344', '956694154454548480', 'button', null, '2018-01-29 15:56:04');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(48) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `role_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`) USING BTREE,
  UNIQUE KEY `role_value` (`role_value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('954227154406969344', '系统管理员', null, '2018-01-19 13:40:57', 'SysAdmin');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(48) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` int(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` varchar(20) DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('953521191379034112', 'sysAdmin', '570ef1c03db8c0e38b675c8365708c9bdae4bcb50c24ca6da345df2062f73e2c72b6437cc8c3447b', null, '13365301652', '1', null, null, '2018-01-17 14:55:42', null);
INSERT INTO `sys_user` VALUES ('955396711205126144', 'admin', '42b9fe23e7d016ce19ac2f1e519543df96ac18028ca380662437c8a918b0838f7e5b545e672f369d', null, null, '1', null, null, '2018-01-22 19:08:21', null);

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` varchar(48) NOT NULL,
  `user_id` varchar(48) DEFAULT NULL,
  `dept_id` varchar(48) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(48) NOT NULL,
  `user_id` varchar(48) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(48) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('955992034835177472', '953521191379034112', '954227154406969344');
