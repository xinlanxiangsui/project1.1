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

 Date: 18/10/2020 13:35:03
*/


-- ----------------------------
-- Table structure for STUDENT
-- ----------------------------
DROP TABLE "SYSTEM"."STUDENT";
CREATE TABLE "SYSTEM"."STUDENT" (
  "STU_ID" NUMBER(10,0) VISIBLE,
  "STU_NUM" NUMBER(5,0) VISIBLE,
  "STU_NAME" VARCHAR2(10 BYTE) VISIBLE,
  "STU_SEX" VARCHAR2(5 BYTE) VISIBLE,
  "STU_ACCOUNT" VARCHAR2(5 BYTE) VISIBLE,
  "STU_PASSWORD" VARCHAR2(5 BYTE) VISIBLE
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
