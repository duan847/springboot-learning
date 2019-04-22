DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `score` decimal(4,1) DEFAULT NULL,
	`student_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('赵', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('钱', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('孙', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('李', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('周', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('吴', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('郑', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('王', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('冯', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('陈', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('褚', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('卫', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('蒋', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('沈', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('韩', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('杨', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('朱', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('秦', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('尤', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('许', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('何', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('吕', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('施', '2019-04-18 15:45:42');
INSERT INTO `test`.`student`(`name`, `update_time`) VALUES ('张', '2019-04-18 15:45:42');

INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 70.0, 1);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 80.0, 1);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 90.0, 1);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 60.0, 2);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 100.0, 2);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 90.0, 2);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 85.5, 3);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 80.0, 3);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 90.0, 3);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 65.5, 4);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 80.0, 4);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 90.0, 4);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 70.0, 5);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 80.0, 5);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 100.0, 5);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('语文', 80.0, 6);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('数学', 80.0, 6);
INSERT INTO `test`.`score`(`course_name`, `score`, `student_id`) VALUES ('英语', 80.0, 6);
