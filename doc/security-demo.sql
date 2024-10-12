-- 创建数据库
CREATE DATABASE `security-demo`;
USE `security-demo`;

-- 创建用户表
CREATE TABLE `user`
(
    `id`       INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50)  DEFAULT NULL,
    `password` VARCHAR(500) DEFAULT NULL,
    `enabled`  BOOLEAN NOT NULL
);
-- 唯一索引
CREATE UNIQUE INDEX `user_username_uindex` ON `user` (`username`);

-- 插入用户数据(密码还是 "password"，实例化BCryptPasswordEncoder )
INSERT INTO `user` (`username`, `password`, `enabled`)
VALUES ('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', TRUE),
       ('Helen', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', TRUE),
       ('Tom', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', TRUE);