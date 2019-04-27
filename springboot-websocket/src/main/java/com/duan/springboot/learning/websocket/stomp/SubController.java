package com.duan.springboot.learning.websocket.stomp;

import com.duan.springboot.learning.websocket.stomp.service.UserSessionService;
import com.duan.springboot.learning.websocket.stomp.vo.ReceiveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
public class SubController {
    @Autowired
    public SimpMessagingTemplate template;
    @Autowired
    public UserSessionService userSessionService;

    @MessageMapping("/subscribe")
    public void subscribe(ReceiveMessage rm) {
        for (int i = 1; i <= 20; i++) {
            //广播使用convertAndSend方法，第一个参数为目的地，和js中订阅的目的地要一致
            template.convertAndSend("/topic/getResponse", rm.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @MessageMapping("/queue")
    public void queuw(ReceiveMessage rm) {
            /*广播使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
            "/user/" + 用户Id + "/message",其中"/user"是固定的*/
        template.convertAndSendToUser(rm.getToId(), "/message", rm.getName());
    }

    @GetMapping("all")
    @ResponseBody
    public List<String> selectAll() {
        List<String> strings = userSessionService.selectAll();
        return strings;
    }
}
