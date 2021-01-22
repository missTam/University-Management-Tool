DROP SCHEMA IF EXISTS `uni-management-db`;

CREATE SCHEMA `uni-management-db`;

use `uni-management-db`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role_id` bigint NOT NULL,

  PRIMARY KEY (`id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `professor`;

CREATE TABLE `professor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `expertise` varchar(45) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  `user_id` bigint NOT NULL,

   PRIMARY KEY (`id`),
   FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `assistant`;

CREATE TABLE `assistant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `professor_id` bigint DEFAULT NULL,

  PRIMARY KEY (`id`),
  KEY `FK_PROFESSOR_idx` (`professor_id`),
  CONSTRAINT `FK_PROFESSOR` FOREIGN KEY (`professor_id`)
  REFERENCES `professor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lecture`;

CREATE TABLE `lecture` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `credits` int(11) NOT NULL,
  `start_date` DATETIME DEFAULT NULL,
  `end_date` DATETIME DEFAULT NULL,
  `professor_id` bigint DEFAULT NULL,

  PRIMARY KEY (`id`),
  KEY `FK_PROFESSOR2_idx` (`professor_id`),
  CONSTRAINT `FK_PROFESSOR2` FOREIGN KEY (`professor_id`)
  REFERENCES `professor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `semester` int(11) NOT NULL,
  `user_id` bigint NOT NULL,

   PRIMARY KEY (`id`),
  FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lecture_student`;

CREATE TABLE `lecture_student` (
  `lecture_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,

  PRIMARY KEY (`lecture_id`,`student_id`),

  KEY `FK_LECTURE_idx` (`lecture_id`),
  CONSTRAINT `FK_LECTURE` FOREIGN KEY (`lecture_id`)
  REFERENCES `lecture` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_STUDENT_idx` (`student_id`),
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`)
  REFERENCES `student` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;