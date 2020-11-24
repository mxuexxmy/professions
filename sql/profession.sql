/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : profession

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 08/07/2020 14:11:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_college
-- ----------------------------
DROP TABLE IF EXISTS `tb_college`;
CREATE TABLE `tb_college`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '学院表',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学院名',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学院信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_college
-- ----------------------------
INSERT INTO `tb_college` VALUES (1, '信息工程学院', '2020-06-15 21:09:08', '2020-06-15 21:09:10');
INSERT INTO `tb_college` VALUES (2, '理学院', '2020-06-15 21:09:32', '2020-06-15 21:09:34');
INSERT INTO `tb_college` VALUES (4, '外国语学院', '2020-06-15 21:10:49', '2020-06-15 21:10:51');
INSERT INTO `tb_college` VALUES (6, '生态工程学院', '2020-07-02 20:58:46', '2020-07-02 20:58:46');
INSERT INTO `tb_college` VALUES (19, '机械工程学院', '2020-07-02 21:22:25', '2020-07-02 21:22:25');

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '描述 iD',
  `category_id` bigint(0) NOT NULL COMMENT '内容类目ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容标题',
  `title_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题描述',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '内容',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '标题内容描述' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES (1, 2, '培养目标', '本专业培养德智体美劳等方面全面发展，掌握软件工程专业所需要基本理论知识，具备用软件工程的思想、方法和技术来分析、设计和实现软件系统的基本能力，具备良好的职业素养、良好的人文素养和沟通、质疑、创新精神，能够在IT行业、科研机构、企事业单位中从事软件工程项目研究、设计、开发、管理、服务等方面的工作，德智体美全面发展，具有认知能力、合作能力、创新能力和职业能力的、立足服务毕节地方经济社会为主的高素质应用型人才。', '<h1>培养目标</h1>', '2020-07-07 09:15:28', '2020-07-07 09:15:33');
INSERT INTO `tb_content` VALUES (2, 5, '核心课程', NULL, '<p>初始化的内容</p>', '2020-07-07 09:16:40', '2020-07-07 09:16:43');
INSERT INTO `tb_content` VALUES (3, 8, '目标1', ' 目标1', '', '2020-07-07 16:06:05', '2020-07-07 16:06:05');
INSERT INTO `tb_content` VALUES (4, 9, '目标2', '目标2', '<h2>目标2</h2>', '2020-07-07 16:07:33', '2020-07-07 16:07:33');
INSERT INTO `tb_content` VALUES (5, 3, '毕业要求', '毕业要求', '<p>不给过</p>', '2020-07-07 17:09:51', '2020-07-07 17:09:51');

-- ----------------------------
-- Table structure for tb_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_category`;
CREATE TABLE `tb_content_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类名称',
  `status` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '状态。可选值:0(正常),1(删除）',
  `sort_order` int(0) NULL DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) NULL DEFAULT NULL COMMENT '该类目是否为父类目，1为true，0为false',
  `audit` int(0) NULL DEFAULT NULL COMMENT '审核状态：0 为审核，1-学院审核，2-系统管理员审核',
  `type` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '内容类型：0 表示目录，1表示内容描述，2课程表，3学分学时表，4表示课程矩阵',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `sort_order`(`sort_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '专业培养方案类目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_content_category
-- ----------------------------
INSERT INTO `tb_content_category` VALUES (1, 0, 3, '17软件工程', 0, 1, 1, 2, 0, '2020-07-03 02:07:37', '2020-07-03 02:07:39');
INSERT INTO `tb_content_category` VALUES (2, 1, 3, '培养目标', 0, 1, 1, 2, 0, '2020-07-04 03:49:44', '2020-07-04 03:49:46');
INSERT INTO `tb_content_category` VALUES (3, 1, 3, '毕业要求', 0, 1, 1, 2, 0, '2020-07-04 03:51:43', '2020-07-04 03:51:45');
INSERT INTO `tb_content_category` VALUES (4, 1, 3, '学制、学位、毕业学分及修业年限', 0, 1, 0, 2, 0, '2020-07-04 03:52:38', '2020-07-04 03:52:41');
INSERT INTO `tb_content_category` VALUES (5, 1, 3, '核心课程', 0, 1, 0, 2, 0, '2020-07-04 03:53:29', '2020-07-04 03:53:32');
INSERT INTO `tb_content_category` VALUES (6, 1, 3, '课程对毕业要求支撑矩阵', 0, 1, 0, 2, 0, '2020-07-04 03:53:51', '2020-07-04 03:53:54');
INSERT INTO `tb_content_category` VALUES (7, 1, 3, '课程教学与学分学时分配表', 0, 1, 0, 2, 0, '2020-07-04 03:54:35', '2020-07-04 03:54:37');
INSERT INTO `tb_content_category` VALUES (8, 2, 3, '目标1', 0, 1, 0, 2, 0, '2020-07-04 03:55:24', '2020-07-04 03:55:27');
INSERT INTO `tb_content_category` VALUES (9, 2, 3, '目标2', 0, 1, 0, 2, 0, '2020-07-04 03:56:02', '2020-07-04 03:56:05');
INSERT INTO `tb_content_category` VALUES (10, 3, 3, '工程知识', 0, 1, 0, 2, 0, '2020-07-04 03:56:38', '2020-07-04 03:56:40');
INSERT INTO `tb_content_category` VALUES (11, 3, 3, '问题分析', 0, 1, 0, 2, 0, '2020-07-04 03:57:09', '2020-07-04 03:57:11');

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `category_id` bigint(0) NOT NULL COMMENT '内容类目ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程名',
  `model` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程模块',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程类别',
  `course_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程号',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_credit_time
-- ----------------------------
DROP TABLE IF EXISTS `tb_credit_time`;
CREATE TABLE `tb_credit_time`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(0) NOT NULL COMMENT '内容类目ID',
  `course_id` bigint(0) NOT NULL COMMENT '课程ID',
  `credit` decimal(3, 2) NULL DEFAULT NULL COMMENT '学分',
  `time` int(0) NULL DEFAULT NULL COMMENT '总学时',
  `classroom` int(0) NULL DEFAULT NULL COMMENT '课程教学学时',
  `experiment` int(0) NULL DEFAULT NULL COMMENT '实验、上机学时',
  `practice` int(0) NULL DEFAULT NULL COMMENT '实习、实践学时',
  `session` int(0) NULL DEFAULT NULL COMMENT '学期分配：1表示第一学期，2表示第二学期，以此类推',
  `check` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '考核方式：考试、考察、证书等',
  `comment` varchar(0) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NOT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学分学时分配表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '年级ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级',
  `college_id` bigint(0) NULL DEFAULT NULL COMMENT '学院ID',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '年级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES (1, '17', 1, '2020-06-15 21:11:17', '2020-06-15 21:11:20');
INSERT INTO `tb_grade` VALUES (2, '18', 1, '2020-06-15 21:11:45', '2020-06-15 21:11:49');
INSERT INTO `tb_grade` VALUES (3, '19', 1, '2020-06-15 21:12:12', '2020-06-15 21:12:14');
INSERT INTO `tb_grade` VALUES (4, '20', 4, '2020-07-03 17:47:46', '2020-07-03 17:47:46');

-- ----------------------------
-- Table structure for tb_profession
-- ----------------------------
DROP TABLE IF EXISTS `tb_profession`;
CREATE TABLE `tb_profession`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `college` bigint(0) NOT NULL COMMENT '学院名',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业名',
  `grade_id` bigint(0) NULL DEFAULT NULL COMMENT '年级ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态。可选值:1(正常),2(删除)',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `college_id`(`college`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '专业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_profession
-- ----------------------------
INSERT INTO `tb_profession` VALUES (1, 1, '软件工程', 1, 3, 1, '2020-06-15 21:13:30', '2020-06-15 21:13:32');
INSERT INTO `tb_profession` VALUES (2, 1, '软件工程', 2, NULL, 1, '2020-06-15 21:15:07', '2020-06-15 21:15:11');
INSERT INTO `tb_profession` VALUES (3, 1, '大数据', 3, NULL, NULL, '2020-07-04 01:20:06', '2020-07-04 01:20:06');

-- ----------------------------
-- Table structure for tb_school
-- ----------------------------
DROP TABLE IF EXISTS `tb_school`;
CREATE TABLE `tb_school`  (
  `id` bigint(0) NOT NULL COMMENT '学校ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学校名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学校表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_school
-- ----------------------------
INSERT INTO `tb_school` VALUES (1, '贵州工程应用技术学院');

-- ----------------------------
-- Table structure for tb_teach_all
-- ----------------------------
DROP TABLE IF EXISTS `tb_teach_all`;
CREATE TABLE `tb_teach_all`  (
  `id` bigint(0) NOT NULL COMMENT '教学模块汇总id',
  `category_id` bigint(0) NOT NULL COMMENT '内容类目ID',
  `nature` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程性质',
  `percent` decimal(20, 3) NULL DEFAULT NULL COMMENT '百分比',
  `credit` decimal(3, 2) NULL DEFAULT NULL COMMENT '学分',
  `time` int(0) NULL DEFAULT NULL COMMENT '学时',
  `theory_time` int(0) NULL DEFAULT NULL COMMENT '理论学时',
  `practice_time` int(0) NULL DEFAULT NULL COMMENT '实践、实验学时',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '教学模块汇总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `usernumber` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '职工号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `college` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '职位，或者专业',
  `authority` int(0) NULL DEFAULT NULL COMMENT '用户权限：0-管理员，1-学院管理员，2-专业负责人',
  `created` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`, `username`, `usernumber`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `college`(`college`) USING BTREE,
  INDEX `title`(`title`) USING BTREE,
  INDEX `usernumber`(`usernumber`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '龙兴', '38381717222', 'e10adc3949ba59abbe56e057f20f883e', '18744981143', '1989718921@qq.com', '信息工程学院', '系统管理员', 0, '2020-06-14 10:26:22', '2020-07-03 02:03:32');
INSERT INTO `tb_user` VALUES (3, 'mxue', '38381717224', 'e10adc3949ba59abbe56e057f20f883e', '11776626253', '11111112@qq.com', '信息工程学院', '软件工程', 2, '2020-07-04 02:34:29', '2020-07-04 02:34:30');
INSERT INTO `tb_user` VALUES (4, '张三', '38381717225', 'e10adc3949ba59abbe56e057f20f883e', '19892817212', '19897188882@qq.com', '理学院', '学院管理员', 1, '2020-06-14 20:11:36', '2020-06-14 20:11:38');
INSERT INTO `tb_user` VALUES (5, '李四', '38381717226', 'e10adc3949ba59abbe56e057f20f883e', '19982827364', '19873618722@qq.com', '化学工程学院', '学院管理员', 1, '2020-06-14 20:12:51', '2020-06-14 20:12:54');
INSERT INTO `tb_user` VALUES (6, 'English', '38381717227', '', '18776266623', 'news@englishclub.com', '外国语学院', '学院管理员', 1, '2020-07-01 20:07:50', '2020-07-01 20:07:50');
INSERT INTO `tb_user` VALUES (7, 'xxmy', '38381717228', 'e10adc3949ba59abbe56e057f20f883e', '17723773634', 'alerts@englishclass101.com', '信息工程学院', '通信工程', 2, '2020-06-14 20:15:58', '2020-06-14 20:16:00');
INSERT INTO `tb_user` VALUES (8, 'test', '38381717230', 'e10adc3949ba59abbe56e057f20f883e', '18287273734', 'alerts@englishclass101.com', '信息工程学院', '计算机科学与技术', 2, '2020-06-14 20:17:03', '2020-06-14 20:17:05');
INSERT INTO `tb_user` VALUES (15, 'xx', '1222222', '123', '111', NULL, '信息工程学院', '大数据', 2, '2020-07-03 13:53:55', '2020-07-03 13:53:55');
INSERT INTO `tb_user` VALUES (16, '1111', '1222222', '123', '111', NULL, '1111', 'xx', 2, '2020-06-23 01:54:59', '2020-06-23 01:54:59');
INSERT INTO `tb_user` VALUES (17, 'ydc', '38381717165', 'e10adc3949ba59abbe56e057f20f883e', '18777651132', 'test@qq.com', '信息工程学院', '学院管理员', 1, '2020-06-23 02:07:08', '2020-06-23 02:07:08');
INSERT INTO `tb_user` VALUES (18, 'test111', '38381718122', 'e10adc3949ba59abbe56e057f20f883e', '18777651132', 'test@qq.com', '外国语学院', '学院管理员', 1, '2020-06-23 02:08:09', '2020-06-23 02:08:09');
INSERT INTO `tb_user` VALUES (19, 'cg', '38381717211', 'e10adc3949ba59abbe56e057f20f883e', '18777651132', 'test@qq.com', '外国语学院', '学院管理员', 1, '2020-06-23 02:12:09', '2020-06-23 02:12:09');
INSERT INTO `tb_user` VALUES (20, '无语', '38381717111', 'e10adc3949ba59abbe56e057f20f883e', '12341221112', 'test@qq.com', '生态工程学院', '学院管理员', 1, '2020-06-27 16:59:02', '2020-06-27 16:59:02');
INSERT INTO `tb_user` VALUES (24, '星期五', '37371212123', 'e10adc3949ba59abbe56e057f20f883e', '12341221112', NULL, '化学工程学院', '学院管理员', 1, '2020-07-02 01:08:54', '2020-07-02 01:08:54');
INSERT INTO `tb_user` VALUES (26, '星期天', '38381717281', 'e10adc3949ba59abbe56e057f20f883e', '15111111112', NULL, '化学工程学院', '学院管理员', 1, '2020-07-02 19:00:33', '2020-07-02 19:00:34');
INSERT INTO `tb_user` VALUES (27, 'weq', '38381717223', 'e10adc3949ba59abbe56e057f20f883e', '15711111111', '14141661661@qq.com', '信息工程学院', '学院管理员', 1, '2020-07-03 02:05:28', '2020-07-03 03:01:42');
INSERT INTO `tb_user` VALUES (28, '李四', '38381717250', 'e10adc3949ba59abbe56e057f20f883e', '18777651132', NULL, '信息工程学院', '信息管理', 2, '2020-07-04 02:11:04', '2020-07-04 02:11:04');

SET FOREIGN_KEY_CHECKS = 1;
