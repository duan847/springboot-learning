DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` bigint(20) NOT NULL,
  `course_name` varchar(32) DEFAULT NULL,
  `score` decimal(5,2) DEFAULT NULL,
  `student_id` bigint(20),
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
