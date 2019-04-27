package com.duan.springboot.learning.websocket.stomp.service.impl;

import com.duan.springboot.learning.websocket.stomp.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author duanjw
 */
@Service
public class RedisUserSessionServiceImpl implements UserSessionService {

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    @Override
    public String get(String id) {
        return redisTemplate.opsForValue().get("user:socket:" + id);
    }

    @Override
    public void add(String id, String value) {
        redisTemplate.opsForValue().set("user:socket:" + id, value);
    }

    @Override
    public void delete(String id) {
        redisTemplate.delete("user:socket:" + id);
    }

    @Override
    public List<String> selectAll() {
        return redisTemplate.opsForValue().multiGet(redisTemplate.keys("user:socket:*"));
    }
}
