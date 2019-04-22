import com.duan.springboot.learning.redis.RedisApplication;
import com.duan.springboot.learning.redis.pojo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * redis的crud操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class RedisListStudentTest {

    @Resource
    private RedisTemplate<String, List<Student>> redisTemplate;

    /**
     * 设置key的值
     */
    @Test
    public void set() {
        List<Student> studentList = Arrays.asList(new Student(1L,"张三儿",new Date()),new Student(2L,"张三儿",new Date()));
        this.redisTemplate.opsForValue().set("student:list", studentList);
    }

    /**
     * 根据key获取值
     */
    @Test
    public void get() {
        log.info("redis中list<Student>：{}", this.redisTemplate.opsForValue().get("student:list"));
    }

}
