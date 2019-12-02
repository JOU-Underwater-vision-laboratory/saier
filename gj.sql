/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hhit

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-04 14:01:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog_content`
-- ----------------------------
# DROP TABLE IF EXISTS `blog_content`;
# CREATE TABLE `blog_content` (
#   `cid` bigint(20) NOT NULL AUTO_INCREMENT,
#   `title` varchar(255) DEFAULT NULL COMMENT '标题',
#   `slug` varchar(255) DEFAULT NULL,
#   `created` bigint(20) DEFAULT NULL COMMENT '创建人id',
#   `modified` bigint(20) DEFAULT NULL COMMENT '最近修改人id',
#   `content` text COMMENT '内容',
#   `type` varchar(16) DEFAULT NULL COMMENT '类型',
#   `tags` varchar(200) DEFAULT NULL COMMENT '标签',
#   `categories` varchar(200) DEFAULT NULL COMMENT '分类',
#   `hits` int(5) DEFAULT NULL,
#   `comments_num` int(5) DEFAULT '0' COMMENT '评论数量',
#   `allow_comment` int(1) DEFAULT '0' COMMENT '开启评论',
#   `allow_ping` int(1) DEFAULT '0' COMMENT '允许ping',
#   `allow_feed` int(1) DEFAULT '0' COMMENT '允许反馈',
#   `status` int(1) DEFAULT NULL COMMENT '状态',
#   `author` varchar(100) DEFAULT NULL COMMENT '作者',
#   `gtm_create` datetime DEFAULT NULL COMMENT '创建时间',
#   `gtm_modified` datetime DEFAULT NULL COMMENT '修改时间',
#   PRIMARY KEY (`cid`)
# ) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='文章内容';


DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `licensenumber` varchar(18) NOT NULL,
  `medicinename` varchar(50) DEFAULT NULL,
  `activeingredient` text,
  `medcharacter` varchar(255) DEFAULT NULL,
  `dose` varchar(50) DEFAULT NULL,
  `dosage` varchar(255) DEFAULT NULL,
  `contraindication` varchar(255) DEFAULT NULL,
  `indication` text,
  `dosagefromdoc` varchar(255) DEFAULT NULL,
  `untowardeffect` varchar(255) DEFAULT NULL,
  `druginteraction` varchar(255) DEFAULT NULL,
  `periodvalidity` varchar(10) DEFAULT NULL,
  `manufacturer` varchar(40) DEFAULT NULL,
  `storageconditions` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`licensenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `medicine` VALUES ('国药准字Z45021599','桂林西瓜霜','西瓜霜、锻硼砂、黄柏、黄连、山豆根、射干、浙贝母、青黛、冰片、大黄、木汉果（炭）黄芩、甘草、薄荷脑','本品为灰黄绿色的粉末；气香，味咸、微苦而辛凉','每瓶装2.5克','喷（吹）敷患处，一次适量，一日数次。\n重症者兼服，一次1～2g，次数视情况而定。','忌烟酒、辛辣、鱼腥食物。','口腔溃疡','喷（吹）敷患处，一次适量，一日数次。','尚不明确','与其他药物可能发生相互作用,请询问医师','36个月','桂林三金药业股份有限公司','密闭，防潮。');


-- ----------------------------
-- Table structure for `oa_notify`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `files` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '附件',
  `status` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知通告';

-- ----------------------------
-- Records of oa_notify
-- ----------------------------
INSERT INTO `oa_notify` VALUES ('41', '3', '十九大召开了', '十九大召开了，竟然没邀请我', '', '1', '1', null, null, '2017-10-10 17:21:11', '', null);

-- ----------------------------
-- Table structure for `oa_notify_record`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `notify_id` bigint(20) DEFAULT NULL COMMENT '通知通告ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '接受人',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '阅读标记',
  `read_date` date DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_id`),
  KEY `oa_notify_record_read_flag` (`is_read`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知通告发送记录';

-- ----------------------------
-- Records of oa_notify_record
-- ----------------------------
INSERT INTO `oa_notify_record` VALUES ('18', '41', '1', '1', '2017-10-26');
INSERT INTO `oa_notify_record` VALUES ('19', '42', '1', '1', '2017-10-26');
INSERT INTO `oa_notify_record` VALUES ('20', '43', '136', '0', null);
INSERT INTO `oa_notify_record` VALUES ('21', '43', '133', '0', null);
INSERT INTO `oa_notify_record` VALUES ('22', '43', '130', '0', null);
INSERT INTO `oa_notify_record` VALUES ('23', '43', '1', '1', '2017-10-26');
INSERT INTO `oa_notify_record` VALUES ('24', '44', '1', '1', '2017-12-29');
INSERT INTO `oa_notify_record` VALUES ('25', '45', '1', '1', '2018-01-02');
INSERT INTO `oa_notify_record` VALUES ('26', '45', '135', '0', null);

-- ----------------------------
-- Table structure for `salary`
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `PROC_INS_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '流程实例ID',
  `USER_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '变动用户',
  `OFFICE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '归属部门',
  `POST` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '岗位',
  `AGE` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `EDU` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '学历',
  `CONTENT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '调整原因',
  `OLDA` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '现行标准 薪酬档级',
  `OLDB` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '现行标准 月工资额',
  `OLDC` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '现行标准 年薪总额',
  `NEWA` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '调整后标准 薪酬档级',
  `NEWB` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '调整后标准 月工资额',
  `NEWC` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '调整后标准 年薪总额',
  `ADD_NUM` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '月增资',
  `EXE_DATE` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '执行时间',
  `HR_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '人力资源部门意见',
  `LEAD_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分管领导意见',
  `MAIN_LEAD_TEXT` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '集团主要领导意见',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `OA_TEST_AUDIT_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='审批流程测试表';

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/site/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('121', '编码', 'code', 'hobby', '爱好', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('122', '绘画', 'painting', 'hobby', '爱好', null, null, null, null, null, null, '', '');

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `open` bit(1) DEFAULT b'0' COMMENT '是否开放下载',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='文件上传';
-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('110', '0', '/files/d64a62e3-6821-48f1-bac6-a1b9945f4afb.jpg', '2017-10-14 16:20:17');
INSERT INTO `sys_file` VALUES ('111', '0', '/files/aa2c3dc6-495f-48cc-8e12-446eceb2535e.jpg', '2017-10-14 16:20:21');
INSERT INTO `sys_file` VALUES ('114', '0', '/files/84c29777-11bc-44b9-818d-859f2d04d417.jpg', '2017-10-20 09:27:18');
INSERT INTO `sys_file` VALUES ('118', '0', '/files/a973499e-3ec7-4d43-8a52-b6f6517c77e3.jpg', '2017-10-20 11:53:52');
INSERT INTO `sys_file` VALUES ('132', '0', '/files/e6f13526-e31c-4ebe-a3cf-5fd88dd10be6.jpg', '2017-12-18 20:19:51');
INSERT INTO `sys_file` VALUES ('134', '0', '/files/cd016e72-77f7-4425-afe2-b79dfbdc3ae9.jpeg', '2017-12-18 22:44:07');
INSERT INTO `sys_file` VALUES ('138', '0', '/files/9ec12ee7-65b5-4cc5-9900-d2ec6185b336.jpg', '2017-12-19 19:55:27');
INSERT INTO `sys_file` VALUES ('139', '0', '/files/f6aab9d4-00a1-4bc8-a695-40fc472d4ea9.jpg', '2018-01-02 19:53:24');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '2', 'test', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 11:33:58');
INSERT INTO `sys_log` VALUES ('2', '2', 'test', '请求访问主页', '286', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 11:33:59');
INSERT INTO `sys_log` VALUES ('3', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 11:55:59');
INSERT INTO `sys_log` VALUES ('4', '1', 'admin', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 11:55:59');
INSERT INTO `sys_log` VALUES ('5', '1', 'admin', '请求访问主页', '11', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 11:56:56');
INSERT INTO `sys_log` VALUES ('6', '2', 'test', '登录', '22', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 12:00:43');
INSERT INTO `sys_log` VALUES ('7', '2', 'test', '请求访问主页', '79', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:00:43');
INSERT INTO `sys_log` VALUES ('8', '2', 'test', '请求访问主页', '15', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:00:52');
INSERT INTO `sys_log` VALUES ('9', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 12:01:03');
INSERT INTO `sys_log` VALUES ('10', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:01:03');
INSERT INTO `sys_log` VALUES ('11', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:01:22');
INSERT INTO `sys_log` VALUES ('12', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:16:26');
INSERT INTO `sys_log` VALUES ('13', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 12:23:19');
INSERT INTO `sys_log` VALUES ('14', '1', 'admin', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:23:20');
INSERT INTO `sys_log` VALUES ('15', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 12:34:03');
INSERT INTO `sys_log` VALUES ('16', '1', 'admin', '请求访问主页', '81', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 12:34:04');
INSERT INTO `sys_log` VALUES ('17', '-1', '获取用户信息为空', '登录', '16', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 20:26:22');
INSERT INTO `sys_log` VALUES ('18', '1', 'admin', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-26 20:26:28');
INSERT INTO `sys_log` VALUES ('19', '1', 'admin', '请求访问主页', '52', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-26 20:26:29');
INSERT INTO `sys_log` VALUES ('20', '1', 'admin', '登录', '47', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 15:33:29');
INSERT INTO `sys_log` VALUES ('21', '1', 'admin', '请求访问主页', '86', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 15:33:30');
INSERT INTO `sys_log` VALUES ('22', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 15:35:01');
INSERT INTO `sys_log` VALUES ('23', '1', 'admin', '请求访问主页', '282', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 15:35:01');
INSERT INTO `sys_log` VALUES ('24', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 16:54:20');
INSERT INTO `sys_log` VALUES ('25', '2', 'test', '请求访问主页', '53', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 16:54:21');
INSERT INTO `sys_log` VALUES ('26', '2', 'test', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:11:16');
INSERT INTO `sys_log` VALUES ('27', '2', 'test', '请求访问主页', '30', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:11:17');
INSERT INTO `sys_log` VALUES ('28', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:13:38');
INSERT INTO `sys_log` VALUES ('29', '2', 'test', '请求访问主页', '33', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:13:38');
INSERT INTO `sys_log` VALUES ('30', '2', 'test', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:14:54');
INSERT INTO `sys_log` VALUES ('31', '2', 'test', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:14:54');
INSERT INTO `sys_log` VALUES ('32', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:17:54');
INSERT INTO `sys_log` VALUES ('33', '2', 'test', '请求访问主页', '40', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:17:55');
INSERT INTO `sys_log` VALUES ('34', '2', 'test', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:22:06');
INSERT INTO `sys_log` VALUES ('35', '2', 'test', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:23:57');
INSERT INTO `sys_log` VALUES ('36', '2', 'test', '请求访问主页', '30', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:23:57');
INSERT INTO `sys_log` VALUES ('37', '2', 'test', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:26:31');
INSERT INTO `sys_log` VALUES ('38', '2', 'test', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:27:11');
INSERT INTO `sys_log` VALUES ('39', '2', 'test', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:29:24');
INSERT INTO `sys_log` VALUES ('40', '2', 'test', '请求访问主页', '307', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:29:25');
INSERT INTO `sys_log` VALUES ('41', '2', 'test', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:31:18');
INSERT INTO `sys_log` VALUES ('42', '2', 'test', '请求访问主页', '40', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:31:18');
INSERT INTO `sys_log` VALUES ('43', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:31:57');
INSERT INTO `sys_log` VALUES ('44', '1', 'admin', '请求访问主页', '22', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:31:57');
INSERT INTO `sys_log` VALUES ('45', '2', 'test', '登录', '2', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:32:08');
INSERT INTO `sys_log` VALUES ('46', '2', 'test', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:32:09');
INSERT INTO `sys_log` VALUES ('47', '2', 'test', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:33:14');
INSERT INTO `sys_log` VALUES ('48', '2', 'test', '请求访问主页', '331', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:33:14');
INSERT INTO `sys_log` VALUES ('49', '2', 'test', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:41:30');
INSERT INTO `sys_log` VALUES ('50', '2', 'test', '请求访问主页', '33', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:41:31');
INSERT INTO `sys_log` VALUES ('51', '2', 'test', '登录', '11', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-29 17:42:26');
INSERT INTO `sys_log` VALUES ('52', '2', 'test', '请求访问主页', '37', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-29 17:42:26');
INSERT INTO `sys_log` VALUES ('53', '2', 'test', '登录', '31', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 11:04:28');
INSERT INTO `sys_log` VALUES ('54', '2', 'test', '请求访问主页', '288', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 11:04:28');
INSERT INTO `sys_log` VALUES ('55', '2', 'test', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 11:32:25');
INSERT INTO `sys_log` VALUES ('56', '2', 'test', '请求访问主页', '79', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 11:32:26');
INSERT INTO `sys_log` VALUES ('57', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 11:42:41');
INSERT INTO `sys_log` VALUES ('58', '2', 'test', '请求访问主页', '35', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 11:42:41');
INSERT INTO `sys_log` VALUES ('59', '2', 'test', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 11:46:15');
INSERT INTO `sys_log` VALUES ('60', '2', 'test', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 11:46:16');
INSERT INTO `sys_log` VALUES ('61', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 11:47:04');
INSERT INTO `sys_log` VALUES ('62', '2', 'test', '请求访问主页', '38', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 11:47:04');
INSERT INTO `sys_log` VALUES ('63', '2', 'test', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:09:16');
INSERT INTO `sys_log` VALUES ('64', '2', 'test', '请求访问主页', '37', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:09:17');
INSERT INTO `sys_log` VALUES ('65', '1', 'admin', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:09:26');
INSERT INTO `sys_log` VALUES ('66', '1', 'admin', '请求访问主页', '46', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:09:27');
INSERT INTO `sys_log` VALUES ('67', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:17:40');
INSERT INTO `sys_log` VALUES ('68', '1', 'admin', '请求访问主页', '26', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:17:40');
INSERT INTO `sys_log` VALUES ('69', '2', 'test', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:21:06');
INSERT INTO `sys_log` VALUES ('70', '2', 'test', '请求访问主页', '36', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:21:06');
INSERT INTO `sys_log` VALUES ('71', '1', 'admin', '登录', '5', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:21:17');
INSERT INTO `sys_log` VALUES ('72', '1', 'admin', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:21:17');
INSERT INTO `sys_log` VALUES ('73', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:28:04');
INSERT INTO `sys_log` VALUES ('74', '1', 'admin', '请求访问主页', '40', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:28:05');
INSERT INTO `sys_log` VALUES ('75', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:36:22');
INSERT INTO `sys_log` VALUES ('76', '1', 'admin', '请求访问主页', '11', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:36:27');
INSERT INTO `sys_log` VALUES ('77', '2', 'test', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 12:43:18');
INSERT INTO `sys_log` VALUES ('78', '2', 'test', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 12:43:18');
INSERT INTO `sys_log` VALUES ('79', '2', 'test', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 16:24:41');
INSERT INTO `sys_log` VALUES ('80', '2', 'test', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 16:24:41');
INSERT INTO `sys_log` VALUES ('81', '1', 'admin', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 16:24:52');
INSERT INTO `sys_log` VALUES ('82', '1', 'admin', '请求访问主页', '22', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 16:24:52');
INSERT INTO `sys_log` VALUES ('83', '1', 'admin', '登录', '49', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 19:34:39');
INSERT INTO `sys_log` VALUES ('84', '1', 'admin', '请求访问主页', '181', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 19:34:40');
INSERT INTO `sys_log` VALUES ('85', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 19:36:22');
INSERT INTO `sys_log` VALUES ('86', '1', 'admin', '请求访问主页', '18', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 19:39:39');
INSERT INTO `sys_log` VALUES ('87', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 19:48:54');
INSERT INTO `sys_log` VALUES ('88', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 19:48:54');
INSERT INTO `sys_log` VALUES ('89', '1', 'admin', '请求访问主页', '9', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 19:49:43');
INSERT INTO `sys_log` VALUES ('90', '1', 'admin', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-30 21:05:22');
INSERT INTO `sys_log` VALUES ('91', '1', 'admin', '请求访问主页', '16', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 21:05:22');
INSERT INTO `sys_log` VALUES ('92', '1', 'admin', '请求访问主页', '8', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-30 21:05:39');
INSERT INTO `sys_log` VALUES ('93', '2', 'test', '登录', '2', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-31 14:56:37');
INSERT INTO `sys_log` VALUES ('94', '2', 'test', '请求访问主页', '44', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-31 14:56:38');
INSERT INTO `sys_log` VALUES ('95', '1', 'admin', '登录', '1', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-31 14:58:39');
INSERT INTO `sys_log` VALUES ('96', '1', 'admin', '请求访问主页', '4', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-31 14:58:40');
INSERT INTO `sys_log` VALUES ('97', '1', 'admin', '请求访问主页', '5', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-31 14:59:26');
INSERT INTO `sys_log` VALUES ('98', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2017-12-31 15:03:53');
INSERT INTO `sys_log` VALUES ('99', '1', 'admin', '请求访问主页', '41', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2017-12-31 15:03:53');
INSERT INTO `sys_log` VALUES ('100', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 08:57:50');
INSERT INTO `sys_log` VALUES ('101', '1', 'admin', '请求访问主页', '71', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 08:57:50');
INSERT INTO `sys_log` VALUES ('102', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 09:45:46');
INSERT INTO `sys_log` VALUES ('103', '1', 'admin', '请求访问主页', '42', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 09:45:46');
INSERT INTO `sys_log` VALUES ('104', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 15:42:15');
INSERT INTO `sys_log` VALUES ('105', '1', 'admin', '请求访问主页', '140', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 15:42:15');
INSERT INTO `sys_log` VALUES ('106', '1', 'admin', '登录', '4', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 15:53:54');
INSERT INTO `sys_log` VALUES ('107', '1', 'admin', '请求访问主页', '23', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 15:53:54');
INSERT INTO `sys_log` VALUES ('108', '1', 'admin', '请求访问主页', '15', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 16:14:20');
INSERT INTO `sys_log` VALUES ('109', '1', 'admin', '登录', '13', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 16:29:03');
INSERT INTO `sys_log` VALUES ('110', '1', 'admin', '请求访问主页', '44', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 16:29:04');
INSERT INTO `sys_log` VALUES ('111', '1', 'admin', '请求访问主页', '27', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 16:30:30');
INSERT INTO `sys_log` VALUES ('112', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:09:49');
INSERT INTO `sys_log` VALUES ('113', '1', 'admin', '请求访问主页', '44', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:09:49');
INSERT INTO `sys_log` VALUES ('114', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:11:05');
INSERT INTO `sys_log` VALUES ('115', '1', 'admin', '请求访问主页', '45', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:11:05');
INSERT INTO `sys_log` VALUES ('116', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:15:11');
INSERT INTO `sys_log` VALUES ('117', '1', 'admin', '请求访问主页', '36', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:15:11');
INSERT INTO `sys_log` VALUES ('118', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:25:34');
INSERT INTO `sys_log` VALUES ('119', '1', 'admin', '请求访问主页', '33', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:25:35');
INSERT INTO `sys_log` VALUES ('120', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:28:02');
INSERT INTO `sys_log` VALUES ('121', '1', 'admin', '请求访问主页', '41', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:28:02');
INSERT INTO `sys_log` VALUES ('122', '1', 'admin', '登录', '2', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 17:46:12');
INSERT INTO `sys_log` VALUES ('123', '1', 'admin', '请求访问主页', '7', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:46:12');
INSERT INTO `sys_log` VALUES ('124', '1', 'admin', '请求访问主页', '9', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 17:56:30');
INSERT INTO `sys_log` VALUES ('125', '1', 'admin', '登录', '62', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 18:00:55');
INSERT INTO `sys_log` VALUES ('126', '1', 'admin', '请求访问主页', '155', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 18:00:55');
INSERT INTO `sys_log` VALUES ('127', '1', 'admin', '登录', '64', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 19:15:24');
INSERT INTO `sys_log` VALUES ('128', '1', 'admin', '请求访问主页', '193', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 19:15:24');
INSERT INTO `sys_log` VALUES ('129', '1', 'admin', '登录', '29', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 19:25:39');
INSERT INTO `sys_log` VALUES ('130', '1', 'admin', '请求访问主页', '177', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 19:25:40');
INSERT INTO `sys_log` VALUES ('131', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 19:33:21');
INSERT INTO `sys_log` VALUES ('132', '1', 'admin', '登录', '5', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-02 19:43:32');
INSERT INTO `sys_log` VALUES ('133', '1', 'admin', '请求访问主页', '44', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-02 19:43:33');
INSERT INTO `sys_log` VALUES ('134', '1', 'admin', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 08:55:21');
INSERT INTO `sys_log` VALUES ('135', '1', 'admin', '请求访问主页', '42', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 08:55:21');
INSERT INTO `sys_log` VALUES ('136', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 09:22:30');
INSERT INTO `sys_log` VALUES ('137', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 09:22:30');
INSERT INTO `sys_log` VALUES ('138', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 09:34:05');
INSERT INTO `sys_log` VALUES ('139', '1', 'admin', '请求访问主页', '36', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 09:34:06');
INSERT INTO `sys_log` VALUES ('140', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 10:20:52');
INSERT INTO `sys_log` VALUES ('141', '1', 'admin', '请求访问主页', '38', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:20:52');
INSERT INTO `sys_log` VALUES ('142', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:25:21');
INSERT INTO `sys_log` VALUES ('143', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:35:33');
INSERT INTO `sys_log` VALUES ('144', '1', 'admin', '登录', '5', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 10:39:20');
INSERT INTO `sys_log` VALUES ('145', '1', 'admin', '请求访问主页', '21', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:39:20');
INSERT INTO `sys_log` VALUES ('146', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:41:47');
INSERT INTO `sys_log` VALUES ('147', '1', 'admin', '请求访问主页', '11', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 10:46:26');
INSERT INTO `sys_log` VALUES ('148', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 11:07:24');
INSERT INTO `sys_log` VALUES ('149', '1', 'admin', '请求访问主页', '39', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 11:07:25');
INSERT INTO `sys_log` VALUES ('153', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 13:05:49');
INSERT INTO `sys_log` VALUES ('154', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 14:07:26');
INSERT INTO `sys_log` VALUES ('155', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 14:07:26');
INSERT INTO `sys_log` VALUES ('156', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 14:36:07');
INSERT INTO `sys_log` VALUES ('157', '1', 'admin', '请求访问主页', '39', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 14:36:07');
INSERT INTO `sys_log` VALUES ('158', '1', 'admin', '请求访问主页', '11', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 14:37:38');
INSERT INTO `sys_log` VALUES ('159', '1', 'admin', '请求访问主页', '303', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 14:37:39');
INSERT INTO `sys_log` VALUES ('160', '-1', '获取用户信息为空', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 17:02:50');
INSERT INTO `sys_log` VALUES ('161', '-1', '获取用户信息为空', '登录', '2', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 17:03:16');
INSERT INTO `sys_log` VALUES ('162', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 17:58:43');
INSERT INTO `sys_log` VALUES ('163', '1', 'admin', '请求访问主页', '43', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 17:58:43');
INSERT INTO `sys_log` VALUES ('164', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 18:06:50');
INSERT INTO `sys_log` VALUES ('165', '1', 'admin', '请求访问主页', '62', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 18:06:50');
INSERT INTO `sys_log` VALUES ('166', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 18:08:49');
INSERT INTO `sys_log` VALUES ('167', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 18:10:41');
INSERT INTO `sys_log` VALUES ('168', '1', 'admin', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 18:10:41');
INSERT INTO `sys_log` VALUES ('169', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 18:10:47');
INSERT INTO `sys_log` VALUES ('170', '1', 'admin', '登录', '15', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:18:39');
INSERT INTO `sys_log` VALUES ('171', '1', 'admin', '请求访问主页', '176', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 19:18:40');
INSERT INTO `sys_log` VALUES ('172', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 19:18:49');
INSERT INTO `sys_log` VALUES ('173', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:44:49');
INSERT INTO `sys_log` VALUES ('174', '1', 'admin', '登录', '24', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:50:12');
INSERT INTO `sys_log` VALUES ('175', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:51:38');
INSERT INTO `sys_log` VALUES ('176', '1', 'admin', '登录', '16', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:53:33');
INSERT INTO `sys_log` VALUES ('177', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:55:04');
INSERT INTO `sys_log` VALUES ('178', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 19:58:54');
INSERT INTO `sys_log` VALUES ('179', '1', 'admin', '登录', '47', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 20:54:38');
INSERT INTO `sys_log` VALUES ('180', '1', 'admin', '请求访问主页', '28', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 20:54:39');
INSERT INTO `sys_log` VALUES ('181', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 21:36:56');
INSERT INTO `sys_log` VALUES ('182', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 21:36:56');
INSERT INTO `sys_log` VALUES ('183', '1', 'admin', '请求访问主页', '11', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 21:37:15');
INSERT INTO `sys_log` VALUES ('184', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 21:39:07');
INSERT INTO `sys_log` VALUES ('185', '1', 'admin', '请求访问主页', '16', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 21:39:07');
INSERT INTO `sys_log` VALUES ('186', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 21:46:18');
INSERT INTO `sys_log` VALUES ('187', '1', 'admin', '登录', '15', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:00:38');
INSERT INTO `sys_log` VALUES ('188', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:01:53');
INSERT INTO `sys_log` VALUES ('189', '1', 'admin', '登录', '16', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:04:27');
INSERT INTO `sys_log` VALUES ('190', '1', 'admin', '登录', '13', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:10:23');
INSERT INTO `sys_log` VALUES ('191', '1', 'admin', '登录', '13', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:13:25');
INSERT INTO `sys_log` VALUES ('192', '1', 'admin', '登录', '15', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:16:08');
INSERT INTO `sys_log` VALUES ('193', '1', 'admin', '登录', '31', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:16:10');
INSERT INTO `sys_log` VALUES ('194', '1', 'admin', '登录', '13', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:23:23');
INSERT INTO `sys_log` VALUES ('195', '1', 'admin', '请求访问主页', '1951', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 22:23:27');
INSERT INTO `sys_log` VALUES ('196', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:36:21');
INSERT INTO `sys_log` VALUES ('197', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:36:21');
INSERT INTO `sys_log` VALUES ('198', '1', 'admin', '登录', '28', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-03 22:36:21');
INSERT INTO `sys_log` VALUES ('199', '1', 'admin', '请求访问主页', '60', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 22:36:21');
INSERT INTO `sys_log` VALUES ('200', '1', 'admin', '请求访问主页', '56', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 22:36:21');
INSERT INTO `sys_log` VALUES ('201', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-03 22:38:04');
INSERT INTO `sys_log` VALUES ('202', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:46:15');
INSERT INTO `sys_log` VALUES ('203', '1', 'admin', '请求访问主页', '36', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:46:15');
INSERT INTO `sys_log` VALUES ('204', '1', 'admin', '登录', '18', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:48:50');
INSERT INTO `sys_log` VALUES ('205', '1', 'admin', '请求访问主页', '43', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:48:50');
INSERT INTO `sys_log` VALUES ('206', '1', 'admin', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:50:17');
INSERT INTO `sys_log` VALUES ('207', '1', 'admin', '请求访问主页', '42', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:50:17');
INSERT INTO `sys_log` VALUES ('208', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:51:11');
INSERT INTO `sys_log` VALUES ('209', '1', 'admin', '请求访问主页', '33', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:51:11');
INSERT INTO `sys_log` VALUES ('210', '1', 'admin', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:53:49');
INSERT INTO `sys_log` VALUES ('211', '1', 'admin', '请求访问主页', '96', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:53:49');
INSERT INTO `sys_log` VALUES ('212', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 08:59:24');
INSERT INTO `sys_log` VALUES ('213', '1', 'admin', '请求访问主页', '35', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 08:59:24');
INSERT INTO `sys_log` VALUES ('214', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:04:28');
INSERT INTO `sys_log` VALUES ('215', '1', 'admin', '请求访问主页', '23', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:04:28');
INSERT INTO `sys_log` VALUES ('216', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:06:22');
INSERT INTO `sys_log` VALUES ('217', '1', 'admin', '请求访问主页', '20', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:06:22');
INSERT INTO `sys_log` VALUES ('218', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:07:43');
INSERT INTO `sys_log` VALUES ('219', '1', 'admin', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:07:44');
INSERT INTO `sys_log` VALUES ('220', '1', 'admin', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:09:31');
INSERT INTO `sys_log` VALUES ('221', '1', 'admin', '请求访问主页', '38', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:09:31');
INSERT INTO `sys_log` VALUES ('222', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:17:12');
INSERT INTO `sys_log` VALUES ('223', '1', 'admin', '请求访问主页', '34', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:17:12');
INSERT INTO `sys_log` VALUES ('224', '1', 'admin', '登录', '0', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:17:48');
INSERT INTO `sys_log` VALUES ('225', '1', 'admin', '请求访问主页', '45', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:17:48');
INSERT INTO `sys_log` VALUES ('226', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:19:20');
INSERT INTO `sys_log` VALUES ('227', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:19:22');
INSERT INTO `sys_log` VALUES ('228', '1', 'admin', '登录', '25', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:20:33');
INSERT INTO `sys_log` VALUES ('229', '1', 'admin', '登录', '17', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:23:46');
INSERT INTO `sys_log` VALUES ('230', '1', 'admin', '登录', '11', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:24:47');
INSERT INTO `sys_log` VALUES ('231', '1', 'admin', '请求访问主页', '30', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:24:47');
INSERT INTO `sys_log` VALUES ('232', '1', 'admin', '登录', '22', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:30:08');
INSERT INTO `sys_log` VALUES ('233', '1', 'admin', '请求访问主页', '41', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:30:08');
INSERT INTO `sys_log` VALUES ('234', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:34:03');
INSERT INTO `sys_log` VALUES ('235', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:34:04');
INSERT INTO `sys_log` VALUES ('236', '1', 'admin', '登录', '19', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 09:35:51');
INSERT INTO `sys_log` VALUES ('237', '1', 'admin', '请求访问主页', '40', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 09:35:52');
INSERT INTO `sys_log` VALUES ('238', '1', 'admin', '登录', '34', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:20');
INSERT INTO `sys_log` VALUES ('239', '1', 'admin', '请求访问主页', '75', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:20');
INSERT INTO `sys_log` VALUES ('240', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:23');
INSERT INTO `sys_log` VALUES ('241', '1', 'admin', '请求访问主页', '63', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:24');
INSERT INTO `sys_log` VALUES ('242', '1', 'admin', '登录', '14', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:27');
INSERT INTO `sys_log` VALUES ('243', '1', 'admin', '请求访问主页', '26', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:27');
INSERT INTO `sys_log` VALUES ('244', '1', 'admin', '登录', '11', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:28');
INSERT INTO `sys_log` VALUES ('245', '1', 'admin', '请求访问主页', '22', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:28');
INSERT INTO `sys_log` VALUES ('246', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:31');
INSERT INTO `sys_log` VALUES ('247', '1', 'admin', '请求访问主页', '25', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:32');
INSERT INTO `sys_log` VALUES ('248', '1', 'admin', '登录', '13', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:35');
INSERT INTO `sys_log` VALUES ('249', '1', 'admin', '请求访问主页', '21', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:35');
INSERT INTO `sys_log` VALUES ('250', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:39');
INSERT INTO `sys_log` VALUES ('251', '1', 'admin', '请求访问主页', '23', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:39');
INSERT INTO `sys_log` VALUES ('252', '1', 'admin', '登录', '16', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:32:52');
INSERT INTO `sys_log` VALUES ('253', '1', 'admin', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:32:52');
INSERT INTO `sys_log` VALUES ('254', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:33:08');
INSERT INTO `sys_log` VALUES ('255', '1', 'admin', '请求访问主页', '21', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:33:08');
INSERT INTO `sys_log` VALUES ('256', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:34:30');
INSERT INTO `sys_log` VALUES ('257', '1', 'admin', '请求访问主页', '23', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:34:30');
INSERT INTO `sys_log` VALUES ('258', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:34:31');
INSERT INTO `sys_log` VALUES ('259', '1', 'admin', '请求访问主页', '40', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:34:32');
INSERT INTO `sys_log` VALUES ('260', '1', 'admin', '登录', '14', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:34:35');
INSERT INTO `sys_log` VALUES ('261', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:34:35');
INSERT INTO `sys_log` VALUES ('262', '1', 'admin', '登录', '30', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:34:35');
INSERT INTO `sys_log` VALUES ('263', '1', 'admin', '请求访问主页', '47', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:34:35');
INSERT INTO `sys_log` VALUES ('264', '1', 'admin', '请求访问主页', '25', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:34:35');
INSERT INTO `sys_log` VALUES ('265', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:35:46');
INSERT INTO `sys_log` VALUES ('266', '1', 'admin', '请求访问主页', '36', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:35:46');
INSERT INTO `sys_log` VALUES ('267', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:35:51');
INSERT INTO `sys_log` VALUES ('268', '1', 'admin', '请求访问主页', '26', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:35:51');
INSERT INTO `sys_log` VALUES ('269', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:36:11');
INSERT INTO `sys_log` VALUES ('270', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:36:11');
INSERT INTO `sys_log` VALUES ('271', '1', 'admin', '登录', '15', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:36:14');
INSERT INTO `sys_log` VALUES ('272', '1', 'admin', '请求访问主页', '23', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:36:14');
INSERT INTO `sys_log` VALUES ('273', '1', 'admin', '登录', '12', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:36:17');
INSERT INTO `sys_log` VALUES ('274', '1', 'admin', '请求访问主页', '17', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:36:17');
INSERT INTO `sys_log` VALUES ('275', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:36:19');
INSERT INTO `sys_log` VALUES ('276', '1', 'admin', '请求访问主页', '28', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:36:20');
INSERT INTO `sys_log` VALUES ('277', '1', 'admin', '登录', '14', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:36:21');
INSERT INTO `sys_log` VALUES ('278', '1', 'admin', '请求访问主页', '19', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:36:21');
INSERT INTO `sys_log` VALUES ('279', '1', 'admin', '登录', '20', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:21');
INSERT INTO `sys_log` VALUES ('280', '1', 'admin', '请求访问主页', '32', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:22');
INSERT INTO `sys_log` VALUES ('281', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:23');
INSERT INTO `sys_log` VALUES ('282', '1', 'admin', '请求访问主页', '27', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:23');
INSERT INTO `sys_log` VALUES ('283', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:27');
INSERT INTO `sys_log` VALUES ('284', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:27');
INSERT INTO `sys_log` VALUES ('285', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:32');
INSERT INTO `sys_log` VALUES ('286', '1', 'admin', '请求访问主页', '20', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:32');
INSERT INTO `sys_log` VALUES ('287', '1', 'admin', '登录', '7', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:37');
INSERT INTO `sys_log` VALUES ('288', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:37');
INSERT INTO `sys_log` VALUES ('289', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:38:42');
INSERT INTO `sys_log` VALUES ('290', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:38:42');
INSERT INTO `sys_log` VALUES ('291', '1', 'admin', '登录', '19', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:24');
INSERT INTO `sys_log` VALUES ('292', '1', 'admin', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:39:24');
INSERT INTO `sys_log` VALUES ('293', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:27');
INSERT INTO `sys_log` VALUES ('294', '1', 'admin', '请求访问主页', '20', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:39:27');
INSERT INTO `sys_log` VALUES ('295', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:32');
INSERT INTO `sys_log` VALUES ('296', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:34');
INSERT INTO `sys_log` VALUES ('297', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:39:34');
INSERT INTO `sys_log` VALUES ('298', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:37');
INSERT INTO `sys_log` VALUES ('299', '1', 'admin', '请求访问主页', '7', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:39:37');
INSERT INTO `sys_log` VALUES ('300', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:39:38');
INSERT INTO `sys_log` VALUES ('301', '1', 'admin', '请求访问主页', '9', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:39:38');
INSERT INTO `sys_log` VALUES ('302', '1', 'admin', '登录', '19', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:40:07');
INSERT INTO `sys_log` VALUES ('303', '1', 'admin', '请求访问主页', '31', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:40:07');
INSERT INTO `sys_log` VALUES ('304', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:40:11');
INSERT INTO `sys_log` VALUES ('305', '1', 'admin', '请求访问主页', '14', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:40:12');
INSERT INTO `sys_log` VALUES ('306', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:40:13');
INSERT INTO `sys_log` VALUES ('307', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:40:14');
INSERT INTO `sys_log` VALUES ('308', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:40:16');
INSERT INTO `sys_log` VALUES ('309', '1', 'admin', '请求访问主页', '18', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:40:17');
INSERT INTO `sys_log` VALUES ('310', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:40:19');
INSERT INTO `sys_log` VALUES ('311', '1', 'admin', '请求访问主页', '13', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:40:20');
INSERT INTO `sys_log` VALUES ('312', '1', 'admin', '登录', '11', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:41:35');
INSERT INTO `sys_log` VALUES ('313', '1', 'admin', '请求访问主页', '16', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:41:35');
INSERT INTO `sys_log` VALUES ('314', '1', 'admin', '登录', '15', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:48:25');
INSERT INTO `sys_log` VALUES ('315', '1', 'admin', '请求访问主页', '26', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:48:25');
INSERT INTO `sys_log` VALUES ('316', '1', 'admin', '登录', '17', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 10:49:37');
INSERT INTO `sys_log` VALUES ('317', '1', 'admin', '请求访问主页', '34', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 10:49:38');
INSERT INTO `sys_log` VALUES ('318', '1', 'admin', '登录', '107', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 11:39:55');
INSERT INTO `sys_log` VALUES ('319', '1', 'admin', '请求访问主页', '52', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 11:39:55');
INSERT INTO `sys_log` VALUES ('320', '1', 'admin', '登录', '45', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 11:44:27');
INSERT INTO `sys_log` VALUES ('321', '1', 'admin', '请求访问主页', '77', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 11:44:27');
INSERT INTO `sys_log` VALUES ('322', '1', 'admin', '登录', '11', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 11:51:26');
INSERT INTO `sys_log` VALUES ('323', '1', 'admin', '请求访问主页', '39', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 11:51:26');
INSERT INTO `sys_log` VALUES ('324', '1', 'admin', '更新用户', '266', 'com.hhitystem.controller.UserController.updatePeronal()', null, '127.0.0.1', '2018-01-04 11:52:15');
INSERT INTO `sys_log` VALUES ('325', '1', 'admin', '登录', '16', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 11:54:09');
INSERT INTO `sys_log` VALUES ('326', '1', 'admin', '请求访问主页', '65', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 11:54:09');
INSERT INTO `sys_log` VALUES ('327', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 11:58:28');
INSERT INTO `sys_log` VALUES ('328', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 11:58:28');
INSERT INTO `sys_log` VALUES ('329', '1', 'admin', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 12:37:42');
INSERT INTO `sys_log` VALUES ('330', '1', 'admin', '请求访问主页', '34', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 12:37:42');
INSERT INTO `sys_log` VALUES ('331', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 12:38:46');
INSERT INTO `sys_log` VALUES ('332', '1', 'admin', '请求访问主页', '14', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 12:41:28');
INSERT INTO `sys_log` VALUES ('333', '1', 'admin', '登录', '3', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 12:48:37');
INSERT INTO `sys_log` VALUES ('334', '1', 'admin', '请求访问主页', '10', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 12:48:37');
INSERT INTO `sys_log` VALUES ('335', '1', 'admin', '登录', '17', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:09:47');
INSERT INTO `sys_log` VALUES ('336', '1', 'admin', '请求访问主页', '67', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:09:47');
INSERT INTO `sys_log` VALUES ('337', '2', 'test', '登录', '22', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:16:42');
INSERT INTO `sys_log` VALUES ('338', '2', 'test', '请求访问主页', '65', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:16:42');
INSERT INTO `sys_log` VALUES ('339', '2', 'test', '请求访问主页', '20', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:16:56');
INSERT INTO `sys_log` VALUES ('340', '2', 'test', '登录', '8', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:20:53');
INSERT INTO `sys_log` VALUES ('341', '2', 'test', '请求访问主页', '42', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:20:53');
INSERT INTO `sys_log` VALUES ('342', '2', 'test', '登录', '22', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:23:23');
INSERT INTO `sys_log` VALUES ('343', '2', 'test', '请求访问主页', '77', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:23:24');
INSERT INTO `sys_log` VALUES ('344', '2', 'test', '登录', '19', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:29:52');
INSERT INTO `sys_log` VALUES ('345', '2', 'test', '请求访问主页', '66', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:29:53');
INSERT INTO `sys_log` VALUES ('346', '-1', '获取用户信息为空', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:37:51');
INSERT INTO `sys_log` VALUES ('347', '1', 'admin', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:37:55');
INSERT INTO `sys_log` VALUES ('348', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:37:55');
INSERT INTO `sys_log` VALUES ('349', '1', 'admin', '批量删除角色', '144', 'com.hhitystem.controller.RoleController.batchRemove()', null, '127.0.0.1', '2018-01-04 13:38:20');
INSERT INTO `sys_log` VALUES ('350', '1', 'admin', '登录', '10', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:48:13');
INSERT INTO `sys_log` VALUES ('351', '1', 'admin', '请求访问主页', '24', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:48:13');
INSERT INTO `sys_log` VALUES ('352', '1', 'admin', '添加角色', '0', 'com.hhitystem.controller.RoleController.add()', null, '127.0.0.1', '2018-01-04 13:48:18');
INSERT INTO `sys_log` VALUES ('353', '1', 'admin', '保存角色', '155', 'com.hhitystem.controller.RoleController.save()', null, '127.0.0.1', '2018-01-04 13:48:24');
INSERT INTO `sys_log` VALUES ('354', '1', 'admin', '添加角色', '0', 'com.hhitystem.controller.RoleController.add()', null, '127.0.0.1', '2018-01-04 13:48:25');
INSERT INTO `sys_log` VALUES ('355', '1', 'admin', '保存角色', '160', 'com.hhitystem.controller.RoleController.save()', null, '127.0.0.1', '2018-01-04 13:48:29');
INSERT INTO `sys_log` VALUES ('356', '1', 'admin', '批量删除角色', '127', 'com.hhitystem.controller.RoleController.batchRemove()', null, '127.0.0.1', '2018-01-04 13:48:35');
INSERT INTO `sys_log` VALUES ('357', '1', 'admin', '提交更改用户密码', '3', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:51:24');
INSERT INTO `sys_log` VALUES ('358', '1', 'admin', '提交更改用户密码', '4', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:51:30');
INSERT INTO `sys_log` VALUES ('359', '1', 'admin', '提交更改用户密码', '4', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:51:46');
INSERT INTO `sys_log` VALUES ('360', '1', 'admin', '提交更改用户密码', '3', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:52:19');
INSERT INTO `sys_log` VALUES ('361', '1', 'admin', '请求更改用户密码', '0', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:54:39');
INSERT INTO `sys_log` VALUES ('362', '1', 'admin', 'admin提交更改用户密码', '7', 'com.hhitystem.controller.UserController.adminResetPwd()', null, '127.0.0.1', '2018-01-04 13:54:47');
INSERT INTO `sys_log` VALUES ('363', '1', 'admin', '登录', '9', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:55:32');
INSERT INTO `sys_log` VALUES ('364', '1', 'admin', '请求访问主页', '31', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:55:32');
INSERT INTO `sys_log` VALUES ('365', '1', 'admin', '提交更改用户密码', '23782', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:56:09');
INSERT INTO `sys_log` VALUES ('366', '-1', '获取用户信息为空', '登录', '6', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:57:18');
INSERT INTO `sys_log` VALUES ('367', '1', 'admin', '登录', '5', 'com.hhitystem.controller.LoginController.ajaxLogin()', null, '127.0.0.1', '2018-01-04 13:57:22');
INSERT INTO `sys_log` VALUES ('368', '1', 'admin', '请求访问主页', '29', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:57:22');
INSERT INTO `sys_log` VALUES ('369', '1', 'admin', '提交更改用户密码', '147', 'com.hhitystem.controller.UserController.resetPwd()', null, '127.0.0.1', '2018-01-04 13:57:35');
INSERT INTO `sys_log` VALUES ('370', '1', 'admin', '请求访问主页', '12', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:57:39');
INSERT INTO `sys_log` VALUES ('371', '1', 'admin', '请求访问主页', '15', 'com.hhitystem.controller.LoginController.index()', null, '127.0.0.1', '2018-01-04 13:57:40');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('2', '3', '系统菜单', 'sys/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO `sys_menu` VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('6', '3', '用户管理', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO `sys_menu` VALUES ('7', '3', '角色管理', 'sys/role', 'sys:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO `sys_menu` VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0', '2017-08-14 10:51:35', null);
INSERT INTO `sys_menu` VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0', '2017-08-14 10:52:06', null);
INSERT INTO `sys_menu` VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO `sys_menu` VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0', '2017-08-14 10:56:37', null);
INSERT INTO `sys_menu` VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0', '2017-08-14 10:59:32', null);
INSERT INTO `sys_menu` VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null);
INSERT INTO `sys_menu` VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null);
INSERT INTO `sys_menu` VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO `sys_menu` VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO `sys_menu` VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO `sys_menu` VALUES ('27', '91', '系统日志', 'common/log', 'common:log', '1', 'fa fa-warning', '0', '2017-08-14 22:11:53', null);
INSERT INTO `sys_menu` VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO `sys_menu` VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO `sys_menu` VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '0', '2017-08-14 22:31:02', null);
INSERT INTO `sys_menu` VALUES ('48', '77', '代码生成', 'common/generator', 'common:generator', '1', 'fa fa-code', '3', null, null);
INSERT INTO `sys_menu` VALUES ('49', '0', '博客管理', '', '', '0', 'fa fa-rss', '6', null, null);
INSERT INTO `sys_menu` VALUES ('50', '49', '文章列表', 'site/bContent', 'site:bContent:bContent', '1', 'fa fa-file-image-o', '1', null, null);
INSERT INTO `sys_menu` VALUES ('51', '50', '新增', '', 'site:bContent:add', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('57', '91', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', null, null);
INSERT INTO `sys_menu` VALUES ('58', '50', '编辑', '', 'site:bContent:edit', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('59', '50', '删除', '', 'site:bContent:remove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('60', '50', '批量删除', '', 'site:bContent:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('68', '49', '发布文章', '/site/bContent/add', 'site:bContent:add', '1', 'fa fa-edit', '0', null, null);
INSERT INTO `sys_menu` VALUES ('71', '1', '文件管理', '/common/sysFile', 'common:sysFile:sysFile', '1', 'fa fa-folder-open', '2', null, null);
INSERT INTO `sys_menu` VALUES ('72', '77', '计划任务', 'common/job', 'common:taskScheduleJob', '1', 'fa fa-hourglass-1', '4', null, null);
INSERT INTO `sys_menu` VALUES ('73', '3', '部门管理', '/system/sysDept', 'system:sysDept:sysDept', '1', 'fa fa-users', '3', null, null);
INSERT INTO `sys_menu` VALUES ('74', '73', '增加', '/system/sysDept/add', 'system:sysDept:add', '2', null, '1', null, null);
INSERT INTO `sys_menu` VALUES ('75', '73', '刪除', 'system/sysDept/remove', 'system:sysDept:remove', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('76', '73', '编辑', '/system/sysDept/edit', 'system:sysDept:edit', '2', null, '3', null, null);
INSERT INTO `sys_menu` VALUES ('77', '0', '系统工具', '', '', '0', 'fa fa-gear', '4', null, null);
INSERT INTO `sys_menu` VALUES ('78', '1', '数据字典', '/common/dict', 'common:dict:dict', '1', 'fa fa-book', '1', null, null);
INSERT INTO `sys_menu` VALUES ('79', '78', '增加', '/common/dict/add', 'common:dict:add', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('80', '78', '编辑', '/common/dict/edit', 'common:dict:edit', '2', null, '2', null, null);
INSERT INTO `sys_menu` VALUES ('81', '78', '删除', '/common/dict/remove', 'common:dict:remove', '2', '', '3', null, null);
INSERT INTO `sys_menu` VALUES ('83', '78', '批量删除', '/common/dict/batchRemove', 'common:dict:batchRemove', '2', '', '4', null, null);
INSERT INTO `sys_menu` VALUES ('84', '0', '办公管理', '', '', '0', 'fa fa-laptop', '5', null, null);
INSERT INTO `sys_menu` VALUES ('85', '84', '通知公告', 'oa/notify', 'oa:notify:notify', '1', 'fa fa-pencil-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('86', '85', '新增', 'oa/notify/add', 'oa:notify:add', '2', 'fa fa-plus', '1', null, null);
INSERT INTO `sys_menu` VALUES ('87', '85', '编辑', 'oa/notify/edit', 'oa:notify:edit', '2', 'fa fa-pencil-square-o', '2', null, null);
INSERT INTO `sys_menu` VALUES ('88', '85', '删除', 'oa/notify/remove', 'oa:notify:remove', '2', 'fa fa-minus', null, null, null);
INSERT INTO `sys_menu` VALUES ('89', '85', '批量删除', 'oa/notify/batchRemove', 'oa:notify:batchRemove', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('90', '84', '我的通知', 'oa/notify/selfNotify', '', '1', 'fa fa-envelope-square', null, null, null);
INSERT INTO `sys_menu` VALUES ('91', '0', '系统监控', '', '', '0', 'fa fa-video-camera', '5', null, null);
INSERT INTO `sys_menu` VALUES ('92', '91', '在线用户', 'sys/online', '', '1', 'fa fa-user', null, null, null);
INSERT INTO `sys_menu` VALUES ('93', '0', '工作流程', '', '', '0', 'fa fa-print', '6', null, null);
INSERT INTO `sys_menu` VALUES ('94', '93', '模型管理', 'activiti/model', '', '1', 'fa fa-sort-amount-asc', null, null, null);
INSERT INTO `sys_menu` VALUES ('95', '94', '全部权限', '', 'activiti:model', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('96', '93', '流程管理', 'activiti/process', '', '1', 'fa fa-flag', null, null, null);
INSERT INTO `sys_menu` VALUES ('97', '0', '图表管理', '', '', '0', 'fa fa-bar-chart', '7', null, null);
INSERT INTO `sys_menu` VALUES ('98', '97', '百度chart', '/chart/graph_echarts.html', '', '1', 'fa fa-area-chart', null, null, null);
INSERT INTO `sys_menu` VALUES ('99', '96', '所有权限', '', 'act:process', '2', '', null, null, null);
INSERT INTO `sys_menu` VALUES ('101', '93', '待办任务', 'activiti/task/todo', '', '1', '', null, null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2988 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '44', '1');
INSERT INTO `sys_role_menu` VALUES ('368', '44', '32');
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33');
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34');
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35');
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28');
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29');
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30');
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38');
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4');
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27');
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38');
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3');
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20');
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21');
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22');
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23');
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11');
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12');
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13');
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14');
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24');
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25');
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26');
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15');
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2');
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6');
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7');
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38');
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42');
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38');
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39');
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40');
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41');
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4');
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32');
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33');
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34');
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35');
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27');
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28');
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29');
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30');
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53');
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2');
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6');
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7');
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3');
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50');
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49');
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1');
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28');
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29');
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30');
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27');
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57');
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71');
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48');
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72');
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1');
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7');
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55');
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56');
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62');
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15');
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2');
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61');
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20');
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21');
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22');
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68');
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60');
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59');
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58');
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51');
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50');
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49');
INSERT INTO `sys_role_menu` VALUES ('2243', '48', '72');
INSERT INTO `sys_role_menu` VALUES ('2247', '63', '-1');
INSERT INTO `sys_role_menu` VALUES ('2248', '63', '84');
INSERT INTO `sys_role_menu` VALUES ('2249', '63', '85');
INSERT INTO `sys_role_menu` VALUES ('2250', '63', '88');
INSERT INTO `sys_role_menu` VALUES ('2251', '63', '87');
INSERT INTO `sys_role_menu` VALUES ('2252', '64', '84');
INSERT INTO `sys_role_menu` VALUES ('2253', '64', '89');
INSERT INTO `sys_role_menu` VALUES ('2254', '64', '88');
INSERT INTO `sys_role_menu` VALUES ('2255', '64', '87');
INSERT INTO `sys_role_menu` VALUES ('2256', '64', '86');
INSERT INTO `sys_role_menu` VALUES ('2257', '64', '85');
INSERT INTO `sys_role_menu` VALUES ('2258', '65', '89');
INSERT INTO `sys_role_menu` VALUES ('2259', '65', '88');
INSERT INTO `sys_role_menu` VALUES ('2260', '65', '86');
INSERT INTO `sys_role_menu` VALUES ('2262', '67', '48');
INSERT INTO `sys_role_menu` VALUES ('2263', '68', '88');
INSERT INTO `sys_role_menu` VALUES ('2264', '68', '87');
INSERT INTO `sys_role_menu` VALUES ('2265', '69', '89');
INSERT INTO `sys_role_menu` VALUES ('2266', '69', '88');
INSERT INTO `sys_role_menu` VALUES ('2267', '69', '86');
INSERT INTO `sys_role_menu` VALUES ('2268', '69', '87');
INSERT INTO `sys_role_menu` VALUES ('2269', '69', '85');
INSERT INTO `sys_role_menu` VALUES ('2270', '69', '84');
INSERT INTO `sys_role_menu` VALUES ('2271', '70', '85');
INSERT INTO `sys_role_menu` VALUES ('2272', '70', '89');
INSERT INTO `sys_role_menu` VALUES ('2273', '70', '88');
INSERT INTO `sys_role_menu` VALUES ('2274', '70', '87');
INSERT INTO `sys_role_menu` VALUES ('2275', '70', '86');
INSERT INTO `sys_role_menu` VALUES ('2276', '70', '84');
INSERT INTO `sys_role_menu` VALUES ('2277', '71', '87');
INSERT INTO `sys_role_menu` VALUES ('2278', '72', '59');
INSERT INTO `sys_role_menu` VALUES ('2279', '73', '48');
INSERT INTO `sys_role_menu` VALUES ('2280', '74', '88');
INSERT INTO `sys_role_menu` VALUES ('2281', '74', '87');
INSERT INTO `sys_role_menu` VALUES ('2282', '75', '88');
INSERT INTO `sys_role_menu` VALUES ('2283', '75', '87');
INSERT INTO `sys_role_menu` VALUES ('2284', '76', '85');
INSERT INTO `sys_role_menu` VALUES ('2285', '76', '89');
INSERT INTO `sys_role_menu` VALUES ('2286', '76', '88');
INSERT INTO `sys_role_menu` VALUES ('2287', '76', '87');
INSERT INTO `sys_role_menu` VALUES ('2288', '76', '86');
INSERT INTO `sys_role_menu` VALUES ('2289', '76', '84');
INSERT INTO `sys_role_menu` VALUES ('2292', '78', '88');
INSERT INTO `sys_role_menu` VALUES ('2293', '78', '87');
INSERT INTO `sys_role_menu` VALUES ('2294', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2295', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2296', '78', null);
INSERT INTO `sys_role_menu` VALUES ('2308', '80', '87');
INSERT INTO `sys_role_menu` VALUES ('2309', '80', '86');
INSERT INTO `sys_role_menu` VALUES ('2310', '80', '-1');
INSERT INTO `sys_role_menu` VALUES ('2311', '80', '84');
INSERT INTO `sys_role_menu` VALUES ('2312', '80', '85');
INSERT INTO `sys_role_menu` VALUES ('2328', '79', '72');
INSERT INTO `sys_role_menu` VALUES ('2329', '79', '48');
INSERT INTO `sys_role_menu` VALUES ('2330', '79', '77');
INSERT INTO `sys_role_menu` VALUES ('2331', '79', '84');
INSERT INTO `sys_role_menu` VALUES ('2332', '79', '89');
INSERT INTO `sys_role_menu` VALUES ('2333', '79', '88');
INSERT INTO `sys_role_menu` VALUES ('2334', '79', '87');
INSERT INTO `sys_role_menu` VALUES ('2335', '79', '86');
INSERT INTO `sys_role_menu` VALUES ('2336', '79', '85');
INSERT INTO `sys_role_menu` VALUES ('2337', '79', '-1');
INSERT INTO `sys_role_menu` VALUES ('2338', '77', '89');
INSERT INTO `sys_role_menu` VALUES ('2339', '77', '88');
INSERT INTO `sys_role_menu` VALUES ('2340', '77', '87');
INSERT INTO `sys_role_menu` VALUES ('2341', '77', '86');
INSERT INTO `sys_role_menu` VALUES ('2342', '77', '85');
INSERT INTO `sys_role_menu` VALUES ('2343', '77', '84');
INSERT INTO `sys_role_menu` VALUES ('2344', '77', '72');
INSERT INTO `sys_role_menu` VALUES ('2345', '77', '-1');
INSERT INTO `sys_role_menu` VALUES ('2346', '77', '77');
INSERT INTO `sys_role_menu` VALUES ('2911', '1', '98');
INSERT INTO `sys_role_menu` VALUES ('2912', '1', '100');
INSERT INTO `sys_role_menu` VALUES ('2913', '1', '99');
INSERT INTO `sys_role_menu` VALUES ('2914', '1', '95');
INSERT INTO `sys_role_menu` VALUES ('2915', '1', '92');
INSERT INTO `sys_role_menu` VALUES ('2916', '1', '57');
INSERT INTO `sys_role_menu` VALUES ('2917', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('2918', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('2919', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('2920', '1', '90');
INSERT INTO `sys_role_menu` VALUES ('2921', '1', '89');
INSERT INTO `sys_role_menu` VALUES ('2922', '1', '88');
INSERT INTO `sys_role_menu` VALUES ('2923', '1', '87');
INSERT INTO `sys_role_menu` VALUES ('2924', '1', '86');
INSERT INTO `sys_role_menu` VALUES ('2925', '1', '72');
INSERT INTO `sys_role_menu` VALUES ('2926', '1', '48');
INSERT INTO `sys_role_menu` VALUES ('2927', '1', '68');
INSERT INTO `sys_role_menu` VALUES ('2928', '1', '60');
INSERT INTO `sys_role_menu` VALUES ('2929', '1', '59');
INSERT INTO `sys_role_menu` VALUES ('2930', '1', '58');
INSERT INTO `sys_role_menu` VALUES ('2931', '1', '51');
INSERT INTO `sys_role_menu` VALUES ('2932', '1', '76');
INSERT INTO `sys_role_menu` VALUES ('2933', '1', '75');
INSERT INTO `sys_role_menu` VALUES ('2934', '1', '74');
INSERT INTO `sys_role_menu` VALUES ('2935', '1', '62');
INSERT INTO `sys_role_menu` VALUES ('2936', '1', '56');
INSERT INTO `sys_role_menu` VALUES ('2937', '1', '55');
INSERT INTO `sys_role_menu` VALUES ('2938', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('2939', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('2940', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('2941', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('2942', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('2943', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('2944', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('2945', '1', '61');
INSERT INTO `sys_role_menu` VALUES ('2946', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('2947', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('2948', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('2949', '1', '83');
INSERT INTO `sys_role_menu` VALUES ('2950', '1', '81');
INSERT INTO `sys_role_menu` VALUES ('2951', '1', '80');
INSERT INTO `sys_role_menu` VALUES ('2952', '1', '79');
INSERT INTO `sys_role_menu` VALUES ('2953', '1', '71');
INSERT INTO `sys_role_menu` VALUES ('2954', '1', '97');
INSERT INTO `sys_role_menu` VALUES ('2955', '1', '96');
INSERT INTO `sys_role_menu` VALUES ('2956', '1', '94');
INSERT INTO `sys_role_menu` VALUES ('2957', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('2958', '1', '91');
INSERT INTO `sys_role_menu` VALUES ('2959', '1', '85');
INSERT INTO `sys_role_menu` VALUES ('2960', '1', '84');
INSERT INTO `sys_role_menu` VALUES ('2961', '1', '77');
INSERT INTO `sys_role_menu` VALUES ('2962', '1', '50');
INSERT INTO `sys_role_menu` VALUES ('2963', '1', '49');
INSERT INTO `sys_role_menu` VALUES ('2964', '1', '73');
INSERT INTO `sys_role_menu` VALUES ('2965', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('2966', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('2967', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('2968', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('2969', '1', '78');
INSERT INTO `sys_role_menu` VALUES ('2970', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2971', '1', '101');
INSERT INTO `sys_role_menu` VALUES ('2972', '1', '93');
INSERT INTO `sys_role_menu` VALUES ('2973', '1', '-1');
INSERT INTO `sys_role_menu` VALUES ('2974', '57', '93');
INSERT INTO `sys_role_menu` VALUES ('2975', '57', '99');
INSERT INTO `sys_role_menu` VALUES ('2976', '57', '95');
INSERT INTO `sys_role_menu` VALUES ('2977', '57', '101');
INSERT INTO `sys_role_menu` VALUES ('2978', '57', '96');
INSERT INTO `sys_role_menu` VALUES ('2979', '57', '94');
INSERT INTO `sys_role_menu` VALUES ('2980', '57', '-1');
INSERT INTO `sys_role_menu` VALUES ('2981', '58', '93');
INSERT INTO `sys_role_menu` VALUES ('2982', '58', '99');
INSERT INTO `sys_role_menu` VALUES ('2983', '58', '95');
INSERT INTO `sys_role_menu` VALUES ('2984', '58', '101');
INSERT INTO `sys_role_menu` VALUES ('2985', '58', '96');
INSERT INTO `sys_role_menu` VALUES ('2986', '58', '94');
INSERT INTO `sys_role_menu` VALUES ('2987', '58', '-1');

-- ----------------------------
-- Table structure for `sys_task`
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('2', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.hhitommon.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '27bd386e70f280e24c2f4f2a549b82cf', '6', 'admin@example.com', '17699999999', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '138', 'ccc', '121;', '北京市', '北京市市辖区', '东城区');
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@hhit.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_user_plus`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_plus`;
CREATE TABLE `sys_user_plus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `payment` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_plus
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1');
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');
INSERT INTO `sys_user_role` VALUES ('125', '132', '52');
INSERT INTO `sys_user_role` VALUES ('126', '132', '49');
INSERT INTO `sys_user_role` VALUES ('127', '123', '48');
INSERT INTO `sys_user_role` VALUES ('132', '36', '48');
INSERT INTO `sys_user_role` VALUES ('133', '137', '56');

DROP TABLE IF EXISTS `downPerson`;
CREATE TABLE `downPerson` (
  `person_name` varchar(128) ,
  `person_email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `person_province` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `person_city` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `person_district` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`person_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `sys_file_soft`;
CREATE TABLE `sys_file_soft` (
  `soft_id` int ,
  `soft_create_date` datetime ,
  `soft_url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `soft_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `soft_introduction` text COLLATE utf8_bin DEFAULT NULL,
  `soft_keywords` text COLLATE utf8_bin DEFAULT NULL,
  `soft_function` text COLLATE utf8_bin DEFAULT NULL,
  `soft_author` text COLLATE utf8_bin DEFAULT NULL,
  `soft_authorEmail` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`soft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `sys_soft_pic`;
CREATE TABLE `sys_soft_pic` (
  `soft_fid` int ,
  `soft_pic01` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`soft_fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


