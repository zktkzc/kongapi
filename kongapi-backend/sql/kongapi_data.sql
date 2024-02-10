/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : kongapi

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 10/02/2024 12:43:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接口名称',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口描述',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '接口地址',
  `requestHeader` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求头',
  `responseHeader` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '响应头',
  `status` int NOT NULL DEFAULT 0 COMMENT '接口状态，0-关闭，1-开启',
  `method` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求类型',
  `userId` bigint NOT NULL COMMENT '用户id',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `requestParams` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接口信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES (1, '许擎宇', '薛聪健', 'www.cary-king.net', '潘博涛', '谭聪健', 1, '石炫明', 9500534531, 0, '2024-02-06 16:28:44', '2024-02-08 12:18:54', NULL);
INSERT INTO `interface_info` VALUES (2, '陆弘文', '白志强', 'www.leslee-kuhn.net', '潘懿轩', '马鸿涛', 0, '陈峻熙', 3982575846, 0, '2024-02-06 16:28:44', '2024-02-06 16:28:44', NULL);
INSERT INTO `interface_info` VALUES (3, '毛建辉', '罗文', 'www.rosaria-kilback.io', '冯子默', '彭哲瀚', 0, '赵远航', 121776355, 0, '2024-02-06 16:28:44', '2024-02-06 16:28:44', NULL);
INSERT INTO `interface_info` VALUES (4, '彭雨泽', '蔡煜祺', 'www.norris-bergstrom.biz', '董思源', '田晓博', 0, '潘擎宇', 740, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (5, '傅志强', '陈梓晨', 'www.jordan-reinger.com', '金志强', '熊锦程', 0, '邓睿渊', 35542559, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (6, '吕黎昕', '孔越彬', 'www.fe-okon.info', '万伟宸', '林昊然', 0, '孟荣轩', 1445, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (7, '夏雪松', '许子骞', 'www.lashawna-legros.co', '蔡昊然', '胡鹏涛', 0, '钟立辉', 34075514, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (8, '严钰轩', '阎志泽', 'www.kay-funk.biz', '莫皓轩', '郭黎昕', 0, '龚天宇', 70956, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (9, '萧嘉懿', '曹熠彤', 'www.margarette-lindgren.biz', '田泽洋', '邓睿渊', 0, '梁志强', 98, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (10, '杜驰', '冯思源', 'www.vashti-auer.org', '黎健柏', '武博文', 0, '李伟宸', 9, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (11, '史金鑫', '蔡鹏涛', 'www.diann-keebler.org', '徐烨霖', '阎建辉', 0, '李烨伟', 125, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (12, '林炫明', '贾旭尧', 'www.dotty-kuvalis.io', '梁雨泽', '龙伟泽', 0, '许智渊', 79998, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (13, '何钰轩', '赖智宸', 'www.andy-adams.net', '崔思淼', '白鸿煊', 0, '邵振家', 7167482751, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (14, '魏志强', '于立诚', 'www.ione-aufderhar.biz', '朱懿轩', '万智渊', 0, '唐昊强', 741098, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (15, '严君浩', '金胤祥', 'www.duane-boyle.org', '雷昊焱', '侯思聪', 0, '郝思', 580514, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (16, '姚皓轩', '金鹏', 'www.lyda-klein.biz', '杜昊强', '邵志泽', 0, '冯鸿涛', 6546, 0, '2024-02-06 16:28:45', '2024-02-06 16:28:45', NULL);
INSERT INTO `interface_info` VALUES (17, '廖驰', '沈泽洋', 'www.consuelo-sipes.info', '彭昊然', '邓耀杰', 0, '周彬', 7761037, 0, '2024-02-06 16:28:46', '2024-02-06 16:28:46', NULL);
INSERT INTO `interface_info` VALUES (18, '赖智渊', '邓志泽', 'www.emerson-mann.co', '熊明哲', '贺哲瀚', 0, '田鹏', 381422, 0, '2024-02-06 16:28:46', '2024-02-06 16:28:46', NULL);
INSERT INTO `interface_info` VALUES (19, '许涛', '陆致远', 'www.vella-ankunding.name', '贾哲瀚', '莫昊焱', 0, '袁越彬', 4218096, 0, '2024-02-06 16:28:46', '2024-02-06 16:28:46', NULL);
INSERT INTO `interface_info` VALUES (20, '吕峻熙', '沈鹏飞', 'www.shari-reichel.org', '郭鸿煊', '覃烨霖', 0, '熊黎昕', 493, 0, '2024-02-06 16:28:46', '2024-02-06 16:28:46', NULL);
INSERT INTO `interface_info` VALUES (21, '测试接口', '测试接口', 'localhost:1234', 'dsav', 'sadvb', 0, 'GET', 1, 1, '2024-02-06 18:11:45', '2024-02-06 18:43:15', NULL);
INSERT INTO `interface_info` VALUES (22, 'asdvdsa', 'asvd', 'localhost:1234', 'dsav', 'asdv', 0, 'GET', 1, 1, '2024-02-06 18:45:58', '2024-02-06 18:46:09', NULL);
INSERT INTO `interface_info` VALUES (23, '测试接口123', '测试接口', 'localhost:1234', 'dsav', 'asdv', 0, 'GET', 1, 1, '2024-02-06 18:47:29', '2024-02-06 19:05:42', NULL);
INSERT INTO `interface_info` VALUES (24, 'getUsernameByPost', '获取用户名', 'http://localhost:8081/api/name/user', '{\"Content-Type\": \"application/json\"}', '{\"Content-Type\": \"application/json\"}', 1, 'POST', 1, 0, '2024-02-08 13:23:07', '2024-02-10 12:24:18', '[{\n  \"name\": \"username\",\n  \"type\": \"string\"\n}]');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `accessKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'accessKey',
  `secretKey` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'secretKey',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`userAccount` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'tkzc00', 'tkzc00', 'https://cn.bing.com/images/search?view=detailV2&ccid=RQrSzMm2&id=1A99CB4356E661B6D6C7841A3CBE342FFDE9A86D&thid=OIP.RQrSzMm2-h5y3TmALl72LQHaH_&mediaurl=https%3a%2f%2fcdn4.vectorstock.com%2fi%2f1000x1000%2f08%2f58%2fcute-shih-tzu-dog-avatar-vector-20670858.jpg&exph=1080&expw=1000&q=avatar+dog&simid=608039547243922340&FORM=IRPRST&ck=FD0B230994C95F85E083CF2836EF5AF1&selectedIndex=3&itb=0&ajaxhist=0&ajaxserp=0', 0, 'admin', 'b0dd3697a192885d7c055db46155b26a', '2024-02-06 15:11:55', '2024-02-06 19:46:45', 0, 'tkzc00', 'abcdefgh');
INSERT INTO `user` VALUES (2, NULL, 'tkzc', NULL, NULL, 'user', 'b0dd3697a192885d7c055db46155b26a', '2024-02-08 13:18:03', '2024-02-08 13:18:03', 0, '91abb37ee9aa6378a77110384e50b0e1', '309f8c174810c13931088bd77dc44fc7');

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint NOT NULL COMMENT '用户id',
  `interfaceInfoId` bigint NOT NULL COMMENT '接口id',
  `totalNum` int NOT NULL DEFAULT 0 COMMENT '总调用次数',
  `leftNum` int NOT NULL DEFAULT 0 COMMENT '剩余调用次数',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态，0-正常，1-禁用',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0-未删除，1-已删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户接口关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------
INSERT INTO `user_interface_info` VALUES (1, 2, 24, 1, 9, 0, 0, '2024-02-10 12:26:28', '2024-02-10 12:28:05');

SET FOREIGN_KEY_CHECKS = 1;
