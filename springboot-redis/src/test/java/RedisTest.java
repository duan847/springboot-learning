import com.duan.springboot.learning.redis.RedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis的crud操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 设置key的值
     */
    @Test
    public void set() {
        this.redisTemplate.opsForValue().set("study", "java");
    }

    /**
     * 设置key的值，并且设置过期时间
     */
    @Test
    public void setAndTimeout() {
        this.redisTemplate.opsForValue().set("study", "java");
        //设置一秒过期
        this.redisTemplate.expire("study", 1, TimeUnit.SECONDS);
    }


    /**
     * 根据通配符查询keys
     */
    @Test
    public void keys() {
        //查询所有key
        log.info("查询redis中所有key的值：{}", this.redisTemplate.keys("*"));
        //查询stud开头的key
        log.info("查询redis中key为stud开头的值：{}", this.redisTemplate.keys("stud*"));
    }

    /**
     * 根据key获取值
     */
    @Test
    public void get() {
        log.info("查询redis中key为study的值：{}", this.redisTemplate.opsForValue().get("study"));
    }

    /**
     * 根据key删除key
     */
    @Test
    public void delete() {
        this.redisTemplate.delete("study");
    }

}
