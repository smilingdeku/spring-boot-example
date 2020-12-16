SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父级 ID',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '类型 [1-菜单] [2-按钮]',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '路径',
  `component` varchar(255) NOT NULL DEFAULT 'Layout' COMMENT '组件',
  `sort_number` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序号',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `permission` varchar(255) NOT NULL COMMENT '权限标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统资源';

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `memo` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 ID',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '资源 ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_resource_id` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色资源';

-- ----------------------------
-- Table structure for t_sys_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_schedule_job`;
CREATE TABLE `t_sys_schedule_job` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '任务名称',
  `group` varchar(255) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组',
  `bean_name` varchar(255) NOT NULL COMMENT 'bean 名称',
  `params` varchar(512) NOT NULL DEFAULT '' COMMENT '参数',
  `cron` varchar(255) NOT NULL COMMENT 'cron 表达式',
  `allow_concurrent` bit(1) NOT NULL DEFAULT b'1' COMMENT '允许并发 [0-不允许] [1-允许]',
  `misfire_policy` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '超时策略 [0-默认] [1-不触发] [2-立刻触发] [3-立刻触发一次]',
  `memo` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态 [0-禁用] [1-启用]',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统定时任务';

-- ----------------------------
-- Table structure for t_sys_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_schedule_job_log`;
CREATE TABLE `t_sys_schedule_job_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_id` bigint(20) unsigned NOT NULL COMMENT '任务 ID',
  `job_name` varchar(255) NOT NULL COMMENT '任务名称',
  `bean_name` varchar(255) NOT NULL COMMENT 'bean 名称',
  `params` varchar(512) NOT NULL COMMENT '参数',
  `spend_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '耗时',
  `message` varchar(512) NOT NULL DEFAULT '' COMMENT '执行记录',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态 [0-失败] [1-成功]',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统定时任务日志';

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `real_name` varchar(255) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '电话',
  `memo` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态 [0-禁用] [1-启用]',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户 ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户角色';

SET FOREIGN_KEY_CHECKS = 1;
