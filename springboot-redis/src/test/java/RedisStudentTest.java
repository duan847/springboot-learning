import com.duan.springboot.learning.redis.RedisApplication;
import com.duan.springboot.learning.redis.pojo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * redis对实体的操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisStudentTest {

    @Resource
    private RedisTemplate<String, Student> redisTemplate;

    /**
     * 设置key的值
     */
    @Test
    public void set() {

        Student Student = new Student(1L,"张三儿",new Date());
        this.redisTemplate.opsForValue().set("Student:1", Student);
    }

    /**
     * 根据key获取值
     */
    @Test
    public void get() {
        log.info("redis中Student：{}",this.redisTemplate.opsForValue().get("Student:1").getName());
    }

}
