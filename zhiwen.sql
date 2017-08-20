/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : zhiwen

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2017-06-12 22:02:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment COMMENT '管理员ID',
  `adminName` varchar(255) NOT NULL COMMENT '管理员账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO admin VALUES ('1', '李展', '123456');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL auto_increment COMMENT '评论ID',
  `titleid` int(11) NOT NULL COMMENT '外键,对应问题表中的qid',
  `user` varchar(255) NOT NULL COMMENT '评论的用户',
  `comment` varchar(255) NOT NULL COMMENT '评论内容',
  `date` date NOT NULL COMMENT '时间',
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO comment VALUES ('1', '1', 'lz', '第一条评论', '2017-06-02');
INSERT INTO comment VALUES ('2', '2', 'lz', '第二条评论', '2017-06-02');
INSERT INTO comment VALUES ('3', '2', 'lz', '第二条评论', '2017-06-02');
INSERT INTO comment VALUES ('4', '2', 'lz', '第二条评论', '2017-06-02');
INSERT INTO comment VALUES ('5', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('6', '3', 'lz', '第四条评论', '2017-06-02');
INSERT INTO comment VALUES ('7', '2', 'lz', '第二条评论怎么了', '2017-06-02');
INSERT INTO comment VALUES ('8', '2', 'lz', '第二条评论怎么了', '2017-06-02');
INSERT INTO comment VALUES ('9', '2', 'lz', '第二条评论怎么了', '2017-06-02');
INSERT INTO comment VALUES ('10', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('11', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('12', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('13', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('14', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('15', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('16', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('17', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('18', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('19', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('20', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('21', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('22', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('23', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('24', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('25', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('26', '3', 'lz', '第三条评论', '2017-06-02');
INSERT INTO comment VALUES ('27', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('28', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('29', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('30', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('31', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('32', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('33', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('34', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('35', '2', 'lz', 'aaa', '2017-06-02');
INSERT INTO comment VALUES ('36', '5', 'lz', '阿里巴巴', '2017-06-02');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `qid` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `content` varchar(3000) default NULL,
  `date` datetime default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`qid`),
  KEY `FK4ekrlbqiybwk8abhgclfjwnmc` (`user_id`),
  CONSTRAINT `FK4ekrlbqiybwk8abhgclfjwnmc` FOREIGN KEY (`user_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO question VALUES ('1', '《加勒比海盗5：死无对证》被遭黑客攻击并盗取成片，你怎么看？', '<p><strong style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\">迪士尼新作《加勒比海盗5:死无对证》资源被黑客盗取，并且受到黑客勒索，体现了许多的问题，这些问题更大程度折射了社会现象</strong></p>', '2017-06-01 11:39:41', '1');
INSERT INTO question VALUES ('2', '戚家军的战斗力竟比电影《荡寇风云》还夸张？还原历史上台州大捷', '<p><strong style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"></strong><span style=\"color: rgb(53, 53, 53); font-family: &quot;Microsoft YaHei&quot;; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 32px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;\">目前正在热映的《荡寇风云》所引起的讨论与关注确实不小。绝大部分的讨论点都在于戚家军与倭寇之间的战斗力对比到底是什么样的？是否真如电影中，面对倭寇连戚继光都被迫陷入苦战，差点殉国吗？还有戚继光的夫人真的那么勇武？</span></p>', '2017-06-01 11:53:53', '1');
INSERT INTO question VALUES ('3', '若人类自己编辑基因组，会引发什么伦理和道德边界？', '<p><strong style=\"color: rgb(51, 51, 51); font-family: &quot;PingFang SC&quot;, &quot;Lantinghei SC&quot;, &quot;Microsoft YaHei&quot;, arial, 宋体, sans-serif, tahoma; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"></strong><span style=\"color: rgb(53, 53, 53); font-family: &quot;Microsoft YaHei&quot;; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 32px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;\"></span><span style=\"color: rgb(53, 53, 53); font-family: &quot;Microsoft YaHei&quot;; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 32px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;\">从来没有一项技术如同基因编辑技术一样让人类既兴奋又紧张。操纵基因即可控制生物性状，这种前景尚不可知的黑科技，可能将为人类带来世世代代不可逆的深远影响。围绕人类基因编辑的科学突破在几年前就已经在科学家的范围内转移了。今天，像CRISPR / Cas9这样的工具允许比以往更有效率和更安全的方式对人类基因组进行修改</span></p><p><span style=\"color: rgb(53, 53, 53); font-family: &quot;Microsoft YaHei&quot;; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 32px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;\">编辑人类基因组的想法提出了科学本身无法应对的问题。人类编辑自己的基因组的伦理和道德边界是什么？治疗严重疾病和增强人类之间的界限在什么地方超出了社会认为的“正常”？这些问题都没有简单明了的答案。需要的是广泛的社会讨论，而不仅仅是关于科学的风险和利益，而且涉及人类基因组编辑周围的道德，政治和社会复杂性</span></p>', '2017-06-01 11:56:34', '1');
INSERT INTO question VALUES ('4', '为何说儿童节是自来不是一个欢乐的故事', '<p>根据报道，六一国际儿童节的设立，和发生在二战期间一次著名的屠杀——利迪策惨案有关。1942年6月10日，德国法西斯枪杀了捷克利迪策村16岁以上的男性公民140余人和全部婴儿，并把妇女和90名儿童押往集中营。村里的房舍、建筑物均被烧毁，好端端的一个村庄就这样被德国法西斯给毁了第1949年11月，国际民主妇女联合会在莫斯科举行理事会议，各国代表愤怒地揭露了帝国主义分子和各国反动派残杀、毒害儿童的罪行。为了保障世界各国儿童的生存权、保健权和受教育权，为了改善儿童的生活，会议决定以每年的6月1日为国际儿童节。当时的很多国家表示赞同，特别是社会主义国家</p>', '2017-06-02 11:58:22', '1');
INSERT INTO question VALUES ('5', '6月1日起贩卖个人信息超过50条即可入罪？', '<p>根据报道，信息泄露的背后大多存在着违法犯罪产业链条，泄露、窃取个人信息数据，非法使用以谋取暴利，6月1日起，《中华人民共和国网络安全法》将正式施行。这部我国第一部全面规范网络空间安全管理方面问题的基础性法律，其中重要的一方面，就是要打击防止公民个人信息数据被非法获取、泄露或者非法使用相关人士介绍，网络安全法是一部基础性法律，而在之前这方面的规范都是一些规章制度，上升为法律后，就为更好的开展网络安全工作提供了法律保障</p>', '2017-06-02 12:03:21', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL auto_increment,
  `userName` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `sex` bit(1) default '' COMMENT '性别',
  `date` datetime default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'lz', 'e10adc3949ba59abbe56e057f20f883e', '1122@qq.com', '', '2017-06-01 00:00:00');
