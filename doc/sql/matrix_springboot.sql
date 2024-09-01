/*
 Navicat Premium Dump SQL

 Source Server         : rocky-mysql
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : 192.168.50.132:3306
 Source Schema         : matrix_springboot

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 01/09/2024 23:10:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '表名',
  `module_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模块名',
  `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '包名',
  `business_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务名',
  `entity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '实体类名',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `parent_menu_id` bigint NULL DEFAULT NULL COMMENT '上级菜单ID，对应sys_menu的id ',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tablename`(`table_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成基础配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_config
-- ----------------------------

-- ----------------------------
-- Table structure for gen_field_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_field_config`;
CREATE TABLE `gen_field_config`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config_id` bigint NOT NULL COMMENT '关联的配置ID',
  `column_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `column_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `column_length` int NULL DEFAULT NULL,
  `field_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字段名称',
  `field_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段类型',
  `field_sort` int NULL DEFAULT NULL COMMENT '字段排序',
  `field_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段描述',
  `max_length` int NULL DEFAULT NULL,
  `is_required` tinyint(1) NULL DEFAULT NULL COMMENT '是否必填',
  `is_show_in_list` tinyint(1) NULL DEFAULT 0 COMMENT '是否在列表显示',
  `is_show_in_form` tinyint(1) NULL DEFAULT 0 COMMENT '是否在表单显示',
  `is_show_in_query` tinyint(1) NULL DEFAULT 0 COMMENT '是否在查询条件显示',
  `query_type` tinyint NULL DEFAULT NULL COMMENT '查询方式',
  `form_type` tinyint NULL DEFAULT NULL COMMENT '表单类型',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典类型',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `config_id`(`config_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成字段配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_field_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `config_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置key',
  `config_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置值',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述、备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `is_deleted` tinyint(1) NOT NULL COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点id',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '父节点id路径',
  `sort` smallint NULL DEFAULT 0 COMMENT '显示顺序',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE COMMENT '部门编号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '贲氏技术', 'BZQ', 0, '0', 1, 1, 1, NULL, 1, '2024-06-24 23:48:59', 0);
INSERT INTO `sys_dept` VALUES (2, '研发部门', 'RD001', 1, '0,1', 1, 1, 2, NULL, 2, '2022-04-19 12:46:37', 0);
INSERT INTO `sys_dept` VALUES (3, '测试部门', 'QA001', 1, '0,1', 1, 1, 2, NULL, 2, '2022-04-19 12:46:37', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '类型编码',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0:正常;1:禁用)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除(1-删除，0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2024-06-22 21:14:47', 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '字典ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项名称',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典项值',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态（1-正常，0-禁用）',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '男', '1', 1, 1, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39');
INSERT INTO `sys_dict_item` VALUES (2, 1, '女', '2', 1, 2, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO `sys_dict_item` VALUES (3, 1, '保密', '0', 1, 3, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module` enum('LOGIN','USER','ROLE','DEPT','MENU','DICT','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志模块',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志内容',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `execution_time` bigint NULL DEFAULT NULL COMMENT '执行时间(ms)',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端系统',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'LOGIN', '登录', '/api/v1/auth/login', '192.168.50.63', '0', '内网IP', 736, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:30:47', 0);
INSERT INTO `sys_log` VALUES (2, 'USER', '用户分页列表', '/api/v1/users/page', '192.168.50.63', '0', '内网IP', 41, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:31:37', 0);
INSERT INTO `sys_log` VALUES (3, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 21, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:31:51', 0);
INSERT INTO `sys_log` VALUES (4, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 19, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:32:00', 0);
INSERT INTO `sys_log` VALUES (5, 'USER', '用户分页列表', '/api/v1/users/page', '192.168.50.63', '0', '内网IP', 21, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:32:32', 0);
INSERT INTO `sys_log` VALUES (6, 'USER', '用户分页列表', '/api/v1/users/page', '192.168.50.63', '0', '内网IP', 22, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:32:48', 0);
INSERT INTO `sys_log` VALUES (7, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 17, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:32:51', 0);
INSERT INTO `sys_log` VALUES (8, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 15, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:32:55', 0);
INSERT INTO `sys_log` VALUES (9, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 16, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 00:33:02', 0);
INSERT INTO `sys_log` VALUES (10, 'LOGIN', '登录', '/api/v1/auth/login', '192.168.50.63', '0', '内网IP', 185, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 08:59:47', 0);
INSERT INTO `sys_log` VALUES (11, 'ROLE', '角色分页列表', '/api/v1/roles/page', '192.168.50.63', '0', '内网IP', 18, 'QQBrowser', '11.5.5240.400', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 08:59:48', 0);
INSERT INTO `sys_log` VALUES (12, 'LOGIN', '登录', '/api/v1/auth/login', '192.168.50.63', '0', '内网IP', 93, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:29:18', 0);
INSERT INTO `sys_log` VALUES (13, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 55, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:30:42', 0);
INSERT INTO `sys_log` VALUES (14, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 22, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:30:51', 0);
INSERT INTO `sys_log` VALUES (15, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 31, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:30:57', 0);
INSERT INTO `sys_log` VALUES (16, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 12, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:08', 0);
INSERT INTO `sys_log` VALUES (17, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 22, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:13', 0);
INSERT INTO `sys_log` VALUES (18, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 22, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:20', 0);
INSERT INTO `sys_log` VALUES (19, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 8, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:24', 0);
INSERT INTO `sys_log` VALUES (20, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 10, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:30', 0);
INSERT INTO `sys_log` VALUES (21, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 9, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:34', 0);
INSERT INTO `sys_log` VALUES (22, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 15, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:42', 0);
INSERT INTO `sys_log` VALUES (23, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 17, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:54', 0);
INSERT INTO `sys_log` VALUES (24, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 13, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:57', 0);
INSERT INTO `sys_log` VALUES (25, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 12, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:31:59', 0);
INSERT INTO `sys_log` VALUES (26, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 11, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:02', 0);
INSERT INTO `sys_log` VALUES (27, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 10, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:06', 0);
INSERT INTO `sys_log` VALUES (28, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 12, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:09', 0);
INSERT INTO `sys_log` VALUES (29, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 10, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:16', 0);
INSERT INTO `sys_log` VALUES (30, 'MENU', '菜单列表', '/api/v1/menus', '192.168.50.63', '0', '内网IP', 13, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:25', 0);
INSERT INTO `sys_log` VALUES (31, 'USER', '用户分页列表', '/api/v1/users/page', '192.168.50.63', '0', '内网IP', 26, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:32:27', 0);
INSERT INTO `sys_log` VALUES (32, 'LOGIN', '注销', '/api/v1/auth/logout', '192.168.50.63', '0', '内网IP', 20, 'Chrome', '127.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2024-08-27 09:35:23', 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint NOT NULL COMMENT '父菜单ID',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `type` tinyint NOT NULL COMMENT '菜单类型（1-菜单 2-目录 3-外链 4-按钮）',
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称（Vue Router 中用于命名路由）',
  `route_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径（Vue Router 中定义的 URL 路径）',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径（组件页面完整路径，相对于 src/views/，缺省后缀 .vue）',
  `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '【按钮】权限标识',
  `always_show` tinyint NULL DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示（1-是 0-否）',
  `keep_alive` tinyint NULL DEFAULT NULL COMMENT '【菜单】是否开启页面缓存（1-是 0-否）',
  `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '显示状态（1-显示 0-隐藏）',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `params` json NULL COMMENT '路由参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', '系统管理', 2, '', '/system', 'Layout', NULL, NULL, NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21', '2024-06-24 23:49:04', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'User', 'user', 'system/user/index', NULL, NULL, 1, 1, 1, 'el-icon-User', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'Role', 'role', 'system/role/index', NULL, NULL, 1, 1, 2, 'role', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL);
INSERT INTO `sys_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'Menu', 'menu', 'system/menu/index', NULL, NULL, 1, 1, 3, 'menu', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, '0,1', '部门管理', 1, 'Dept', 'dept', 'system/dept/index', NULL, NULL, 1, 1, 4, 'tree', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL);
INSERT INTO `sys_menu` VALUES (6, 1, '0,1', '字典管理', 1, 'Dict', 'dict', 'system/dict/index', NULL, NULL, 1, 1, 5, 'dict', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', NULL);
INSERT INTO `sys_menu` VALUES (31, 2, '0,1,2', '用户新增', 4, NULL, '', NULL, 'sys:user:add', NULL, NULL, 1, 1, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL);
INSERT INTO `sys_menu` VALUES (32, 2, '0,1,2', '用户编辑', 4, NULL, '', NULL, 'sys:user:edit', NULL, NULL, 1, 2, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL);
INSERT INTO `sys_menu` VALUES (33, 2, '0,1,2', '用户删除', 4, NULL, '', NULL, 'sys:user:delete', NULL, NULL, 1, 3, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', NULL);
INSERT INTO `sys_menu` VALUES (70, 3, '0,1,3', '角色新增', 4, NULL, '', NULL, 'sys:role:add', NULL, NULL, 1, 1, '', NULL, '2023-05-20 23:39:09', '2023-05-20 23:39:09', NULL);
INSERT INTO `sys_menu` VALUES (71, 3, '0,1,3', '角色编辑', 4, NULL, '', NULL, 'sys:role:edit', NULL, NULL, 1, 2, '', NULL, '2023-05-20 23:40:31', '2023-05-20 23:40:31', NULL);
INSERT INTO `sys_menu` VALUES (72, 3, '0,1,3', '角色删除', 4, NULL, '', NULL, 'sys:role:delete', NULL, NULL, 1, 3, '', NULL, '2023-05-20 23:41:08', '2023-05-20 23:41:08', NULL);
INSERT INTO `sys_menu` VALUES (73, 4, '0,1,4', '菜单新增', 4, NULL, '', NULL, 'sys:menu:add', NULL, NULL, 1, 1, '', NULL, '2023-05-20 23:41:35', '2023-05-20 23:41:35', NULL);
INSERT INTO `sys_menu` VALUES (74, 4, '0,1,4', '菜单编辑', 4, NULL, '', NULL, 'sys:menu:edit', NULL, NULL, 1, 3, '', NULL, '2023-05-20 23:41:58', '2023-05-20 23:41:58', NULL);
INSERT INTO `sys_menu` VALUES (75, 4, '0,1,4', '菜单删除', 4, NULL, '', NULL, 'sys:menu:delete', NULL, NULL, 1, 3, '', NULL, '2023-05-20 23:44:18', '2023-05-20 23:44:18', NULL);
INSERT INTO `sys_menu` VALUES (76, 5, '0,1,5', '部门新增', 4, NULL, '', NULL, 'sys:dept:add', NULL, NULL, 1, 1, '', NULL, '2023-05-20 23:45:00', '2023-05-20 23:45:00', NULL);
INSERT INTO `sys_menu` VALUES (77, 5, '0,1,5', '部门编辑', 4, NULL, '', NULL, 'sys:dept:edit', NULL, NULL, 1, 2, '', NULL, '2023-05-20 23:46:16', '2023-05-20 23:46:16', NULL);
INSERT INTO `sys_menu` VALUES (78, 5, '0,1,5', '部门删除', 4, NULL, '', NULL, 'sys:dept:delete', NULL, NULL, 1, 3, '', NULL, '2023-05-20 23:46:36', '2023-05-20 23:46:36', NULL);
INSERT INTO `sys_menu` VALUES (79, 6, '0,1,6', '字典类型新增', 4, NULL, '', NULL, 'sys:dict_type:add', NULL, NULL, 1, 1, '', NULL, '2023-05-21 00:16:06', '2023-05-21 00:16:06', NULL);
INSERT INTO `sys_menu` VALUES (81, 6, '0,1,6', '字典类型编辑', 4, NULL, '', NULL, 'sys:dict_type:edit', NULL, NULL, 1, 2, '', NULL, '2023-05-21 00:27:37', '2023-05-21 00:27:37', NULL);
INSERT INTO `sys_menu` VALUES (84, 6, '0,1,6', '字典类型删除', 4, NULL, '', NULL, 'sys:dict_type:delete', NULL, NULL, 1, 3, '', NULL, '2023-05-21 00:29:39', '2023-05-21 00:29:39', NULL);
INSERT INTO `sys_menu` VALUES (85, 6, '0,1,6', '字典数据新增', 4, NULL, '', NULL, 'sys:dict:add', NULL, NULL, 1, 4, '', NULL, '2023-05-21 00:46:56', '2023-05-21 00:47:06', NULL);
INSERT INTO `sys_menu` VALUES (86, 6, '0,1,6', '字典数据编辑', 4, NULL, '', NULL, 'sys:dict:edit', NULL, NULL, 1, 5, '', NULL, '2023-05-21 00:47:36', '2023-05-21 00:47:36', NULL);
INSERT INTO `sys_menu` VALUES (87, 6, '0,1,6', '字典数据删除', 4, NULL, '', NULL, 'sys:dict:delete', NULL, NULL, 1, 6, '', NULL, '2023-05-21 00:48:10', '2023-05-21 00:48:20', NULL);
INSERT INTO `sys_menu` VALUES (88, 2, '0,1,2', '重置密码', 4, NULL, '', NULL, 'sys:user:password:reset', NULL, NULL, 1, 4, '', NULL, '2023-05-21 00:49:18', '2024-04-28 00:38:22', NULL);
INSERT INTO `sys_menu` VALUES (105, 2, '0,1,2', '用户查询', 4, NULL, '', NULL, 'sys:user:query', 0, 0, 1, 0, '', NULL, '2024-04-28 00:37:34', '2024-04-28 00:37:34', NULL);
INSERT INTO `sys_menu` VALUES (106, 2, '0,1,2', '用户导入', 4, NULL, '', NULL, 'sys:user:import', NULL, NULL, 1, 5, '', NULL, '2024-04-28 00:39:15', '2024-04-28 00:39:15', NULL);
INSERT INTO `sys_menu` VALUES (107, 2, '0,1,2', '用户导出', 4, NULL, '', NULL, 'sys:user:export', NULL, NULL, 1, 6, '', NULL, '2024-04-28 00:39:43', '2024-04-28 00:39:43', NULL);
INSERT INTO `sys_menu` VALUES (117, 1, '0,1', '系统日志', 1, 'Log', 'log', 'system/log/index', NULL, 0, 1, 1, 6, 'document', NULL, '2024-06-28 07:43:16', '2024-06-28 07:43:16', NULL);

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统消息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常 0-停用)',
  `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据权限(0-所有数据 1-部门及子部门数据 2-本部门数据3-本人数据)',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人 ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE COMMENT '角色名称唯一索引',
  UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, NULL, '2021-05-21 14:56:51', NULL, '2018-12-23 16:00:00', 0);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, NULL, '2021-03-25 12:39:54', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (4, '系统管理员1', 'ADMIN1', 4, 1, 1, NULL, '2021-03-25 12:39:54', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (5, '系统管理员2', 'ADMIN2', 5, 1, 1, NULL, '2021-03-25 12:39:54', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  UNIQUE INDEX `uk_roleid_menuid`(`role_id` ASC, `menu_id` ASC) USING BTREE COMMENT '角色菜单唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 70);
INSERT INTO `sys_role_menu` VALUES (2, 71);
INSERT INTO `sys_role_menu` VALUES (2, 72);
INSERT INTO `sys_role_menu` VALUES (2, 73);
INSERT INTO `sys_role_menu` VALUES (2, 74);
INSERT INTO `sys_role_menu` VALUES (2, 75);
INSERT INTO `sys_role_menu` VALUES (2, 76);
INSERT INTO `sys_role_menu` VALUES (2, 77);
INSERT INTO `sys_role_menu` VALUES (2, 78);
INSERT INTO `sys_role_menu` VALUES (2, 79);
INSERT INTO `sys_role_menu` VALUES (2, 81);
INSERT INTO `sys_role_menu` VALUES (2, 84);
INSERT INTO `sys_role_menu` VALUES (2, 85);
INSERT INTO `sys_role_menu` VALUES (2, 86);
INSERT INTO `sys_role_menu` VALUES (2, 87);
INSERT INTO `sys_role_menu` VALUES (2, 88);
INSERT INTO `sys_role_menu` VALUES (2, 89);
INSERT INTO `sys_role_menu` VALUES (2, 90);
INSERT INTO `sys_role_menu` VALUES (2, 91);
INSERT INTO `sys_role_menu` VALUES (2, 95);
INSERT INTO `sys_role_menu` VALUES (2, 97);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 118);
INSERT INTO `sys_role_menu` VALUES (2, 119);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别((1-男 2-女 0-保密)',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `dept_id` int NULL DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态((1-正常 0-禁用)',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 288 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '贲氏科技', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621590365', 1, 'youlaitech@163.com', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (2, 'admin', '系统管理员', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, '', '2019-10-10 13:41:22', NULL, '2022-07-31 12:39:30', NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'test', '测试用户', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 3, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, 'youlaitech@163.com', '2021-06-05 01:31:29', NULL, '2024-08-27 00:32:47', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  UNIQUE INDEX `uk_userid_roleid`(`user_id` ASC, `role_id` ASC) USING BTREE COMMENT '用户角色唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);

SET FOREIGN_KEY_CHECKS = 1;
