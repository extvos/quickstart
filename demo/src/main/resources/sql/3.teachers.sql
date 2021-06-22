CREATE TABLE IF NOT EXISTS `example_teachers` (
	`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`name` VARCHAR(32) NOT NULL COMMENT '名字' COLLATE 'utf8mb4_general_ci',
	`gender` VARCHAR(4) NOT NULL COMMENT '性别' COLLATE 'utf8mb4_general_ci',
	`phone_number` VARCHAR(18) NOT NULL COMMENT '电话号码' COLLATE 'utf8mb4_general_ci',
	`family_name` VARCHAR(32) NOT NULL COMMENT '姓氏' COLLATE 'utf8mb4_general_ci',
	`age` SMALLINT(6) NOT NULL DEFAULT '0' COMMENT '年龄',
	`birthday` DATE NULL  COMMENT '生日',
	PRIMARY KEY (`id`) USING BTREE
)
COMMENT='样例教师数据表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
;
