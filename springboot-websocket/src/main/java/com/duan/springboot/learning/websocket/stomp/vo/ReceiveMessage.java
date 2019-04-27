package com.duan.springboot.learning.websocket.stomp.vo;

import lombok.Data;

@Data
public class ReceiveMessage {

    private String name;

    private String toId;

}
