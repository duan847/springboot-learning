import com.duan.springboot.learning.redis.RedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * redis的字符串crud操作
 *
 * @auth duanjw
 * @data 2019/04/24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisStringTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String key = "redis:test";

    /**
     * 设置key的值
     */
    @Test
    public void test01set() {
        redisTemplate.opsForValue().set(key, "java");
        //redis中包含刚刚设置的key
        assertThat(redisTemplate.hasKey(key), is(true));
        log.info("设置key成功。");
    }

    /**
     * 设置key的值，并且设置过期时间
     */
    @Test
    public void test02setAndTimeout() {
        redisTemplate.opsForValue().set(key, "java");
        //设置60秒过期
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        //redis中包含刚刚设置的key
        assertThat(redisTemplate.hasKey(key), is(true));
        log.info("设置key成功。");
    }


    /**
     * 根据通配符查询keys
     */
    @Test
    public void test03keys() {
        //查询所有key
        log.info("查询redis中所有key：{}", redisTemplate.keys("*"));
        //查询stud开头的key
        log.info("查询redis中key为redis:t开头的key：{}", redisTemplate.keys("redis:t*"));
    }

    /**
     * 根据key获取值
     */
    @Test
    public void test04get() {
        String value = redisTemplate.opsForValue().get(key);
        //redis中包含刚刚设置的key
        assertThat(redisTemplate.hasKey(key), is(true));
        log.info("查询redis中key为study的值：{}", value);
    }

    /**
     * 根据key删除key
     */
    @Test
    public void test05delete() {
        redisTemplate.delete(key);
        //redis中不包含刚刚设置的key
        assertThat(redisTemplate.hasKey(key), is(false));
        log.info("根据key：{}删除key", key);

    }

}
