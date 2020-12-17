--
-- Table structure for table `t_sys_resource`
--

DROP TABLE IF EXISTS `t_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父级 ID',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '类型 [1-菜单] [2-按钮]',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '路径',
  `component` varchar(255) NOT NULL DEFAULT 'Layout' COMMENT '组件',
  `permission` varchar(255) NOT NULL COMMENT '权限标志',
  `sort_number` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序号',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='系统资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource`
--

LOCK TABLES `t_sys_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
INSERT INTO `t_sys_resource` VALUES (1,0,1,'系统管理','el-icon-setting','/system','Layout','system',1,'2020-12-16 22:55:38',NULL),(2,1,1,'用户管理','','/system/user','system/user/index','system:user',1,'2020-12-17 15:25:46',NULL),(3,2,2,'用户新增','','','Layout','system:user:add',0,'2020-12-17 15:28:08',NULL),(4,2,2,'用户删除','','','Layout','system:user:delete',0,'2020-12-17 15:30:03',NULL),(5,2,2,'用户编辑','','','Layout','system:user:edit',0,'2020-12-17 15:30:42',NULL),(6,1,1,'角色管理','','/system/role','system/role/index','system:role',2,'2020-12-17 15:32:12',NULL),(7,6,2,'角色新增','','','Layout','system:role:add',0,'2020-12-17 15:34:00',NULL),(8,6,2,'角色删除','','','Layout','system:role:delete',0,'2020-12-17 15:35:02',NULL),(9,6,2,'角色编辑','','','Layout','system:role:edit',0,'2020-12-17 15:35:24',NULL),(10,1,1,'资源管理','','/system/resource','system/resource/index','system:resource',3,'2020-12-17 15:36:47',NULL),(11,10,2,'资源新增','','','Layout','system:resource:add',0,'2020-12-17 15:37:52',NULL),(12,10,2,'资源删除','','','Layout','system:resource:delete',0,'2020-12-17 15:38:10',NULL),(13,10,2,'资源编辑','','','Layout','system:resource:edit',0,'2020-12-17 15:39:48',NULL),(14,0,1,'系统监控','el-icon-monitor','/monitor','Layout','monitor',2,'2020-12-17 15:42:40',NULL),(15,14,1,'任务调度','','/monitor/schedule-job','monitor/schedule-job/index','monitor:schedule-job',1,'2020-12-17 15:45:32',NULL),(16,15,2,'任务新增','','','Layout','monitor:schedule-job:add',0,'2020-12-17 15:47:23',NULL),(17,15,2,'任务删除','','','Layout','monitor:schedule-job:delete',0,'2020-12-17 15:48:00',NULL),(18,15,2,'任务编辑','','','Layout','monitor:schedule-job:edit',0,'2020-12-17 15:48:28',NULL),(19,15,2,'任务执行','','','Layout','monitor:schedule-job:run',0,'2020-12-17 15:49:34',NULL),(20,14,1,'调度日志','','/monitor/schedule-job-log','monitor/schedule-job-log/index','monitor:schedule-job-log',2,'2020-12-17 15:50:44',NULL),(21,20,2,'日志删除','','','Layout','monitor:schedule-job-log:delete',0,'2020-12-17 15:53:13',NULL);
/*!40000 ALTER TABLE `t_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role`
--

DROP TABLE IF EXISTS `t_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `memo` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` VALUES (1,'系统管理员','','2020-12-16 22:52:22',NULL);
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_resource`
--

DROP TABLE IF EXISTS `t_sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 ID',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '资源 ID',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_resource_id` (`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_resource`
--

LOCK TABLES `t_sys_role_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_role_resource` DISABLE KEYS */;
INSERT INTO `t_sys_role_resource` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21);
/*!40000 ALTER TABLE `t_sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_schedule_job`
--

DROP TABLE IF EXISTS `t_sys_schedule_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_schedule_job`
--

LOCK TABLES `t_sys_schedule_job` WRITE;
/*!40000 ALTER TABLE `t_sys_schedule_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_schedule_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_schedule_job_log`
--

DROP TABLE IF EXISTS `t_sys_schedule_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_schedule_job_log`
--

LOCK TABLES `t_sys_schedule_job_log` WRITE;
/*!40000 ALTER TABLE `t_sys_schedule_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_schedule_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` VALUES (1,'admin','$2a$10$NrVl32Jpd9rXePkpcWcAAOmXBUPXPpYqQ0LfKBsuQEL05jVYmV2Te','$2a$10$yJuGVbqhLwbOM2ItzDiPC.a5ExNPggL3BtK81AFP0E9NtS8zozFye','admin','123456@qq.com','13000000000','',1,'2020-12-16 22:40:04','2020-12-16 22:46:01');
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user_role`
--

DROP TABLE IF EXISTS `t_sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户 ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user_role`
--

LOCK TABLES `t_sys_user_role` WRITE;
/*!40000 ALTER TABLE `t_sys_user_role` DISABLE KEYS */;
INSERT INTO `t_sys_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `t_sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

