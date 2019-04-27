package com.duan.springboot.learning.websocket.stomp.service;

import java.util.List;

/**
 * @author duanjw
 */
public interface UserSessionService {
    String get(String id);
    void add(String id, String value);
    void delete(String id);
    List<String> selectAll();
}
