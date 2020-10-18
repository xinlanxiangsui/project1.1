/*
 Navicat Oracle Data Transfer

 Source Server         : test
 Source Server Type    : Oracle
 Source Server Version : 190000
 Source Host           : localhost:1521
 Source Schema         : SYSTEM

 Target Server Type    : Oracle
 Target Server Version : 190000
 File Encoding         : 65001

 Date: 18/10/2020 13:34:49
*/


-- ----------------------------
-- Table structure for CLOCK
-- ----------------------------
DROP TABLE "SYSTEM"."CLOCK";
CREATE TABLE "SYSTEM"."CLOCK" (
  "CLO_ID" NUMBER(10,0) VISIBLE,
  "CLO_NUM" NUMBER(5,0) VISIBLE,
  "CLO_TEMP" NUMBER(5,0) VISIBLE,
  "CLO_TIME" VARCHAR2(10 BYTE) VISIBLE,
  "CLO_PLACE" VARCHAR2(10 BYTE) VISIBLE,
  "CLO_IDENTITY" VARCHAR2(5 BYTE) VISIBLE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
