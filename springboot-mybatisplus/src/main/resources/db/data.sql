DELETE FROM student;

INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('1','赵', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('2','钱', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('3','孙', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('4','李', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('5','周', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('6','吴', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('7','郑', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('8','王', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('9','冯', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('10','陈', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('11','褚', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('12','卫', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('13','蒋', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('14','沈', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('15','韩', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('16','杨', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('17','朱', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('18','秦', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('19','尤', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('20','许', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('21','何', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('22','吕', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('23','施', '2019-04-18 15:45:42');
INSERT INTO `student`(`id`, `name`, `update_time`) VALUES ('24','张', '2019-04-18 15:45:42');

DELETE FROM score;
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('1', '语文', 70.0, 1);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('2', '数学', 80.0, 1);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('3', '英语', 90.0, 1);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('4', '语文', 60.0, 2);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('5', '数学', 100.0, 2);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('6', '英语', 90.0, 2);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('7', '语文', 85.5, 3);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('8', '数学', 80.0, 3);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('9', '英语', 90.0, 3);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('10', '语文', 65.5, 4);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('11', '数学', 80.0, 4);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('12', '英语', 90.0, 4);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('13', '语文', 70.0, 5);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('14', '数学', 80.0, 5);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('15', '英语', 100.0, 5);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('16', '语文', 80.0, 6);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('17', '数学', 80.0, 6);
INSERT INTO `score`(`id`, `course_name`, `score`, `student_id`) VALUES ('18', '英语', 80.0, 6);


DELETE FROM teacher;

INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('1','赵老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('2','钱老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('3','孙老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('4','李老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('5','周老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('6','吴老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('7','郑老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('8','王老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('9','冯老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('10','陈老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('11','褚老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('12','卫老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('13','蒋老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('14','沈老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('15','韩老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('16','杨老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('17','朱老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('18','秦老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('19','尤老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('20','许老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('21','何老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('22','吕老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('23','施老师', '2019-04-18 15:45:42');
INSERT INTO `teacher`(`id`, `name`, `update_time`) VALUES ('24','张老师', '2019-04-18 15:45:42');