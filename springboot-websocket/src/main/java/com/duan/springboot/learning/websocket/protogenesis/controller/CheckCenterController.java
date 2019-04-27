package com.duan.springboot.learning.websocket.protogenesis.controller;

import com.duan.springboot.learning.websocket.protogenesis.server.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author duanjw
 */
@Controller
@RequestMapping("/websocket")
public class CheckCenterController {

    /**
     * 页面请求
     * @param cid
     * @return
     */
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    /**
     * 推送数据接口
     *
     * @param cid
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return "true";
    }
}
