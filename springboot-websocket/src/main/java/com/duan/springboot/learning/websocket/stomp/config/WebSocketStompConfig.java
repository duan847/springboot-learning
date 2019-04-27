package com.duan.springboot.learning.websocket.stomp.config;

import com.duan.springboot.learning.websocket.stomp.service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * @author duanjw
 *  //注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private UserSessionService userSessionService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //topic用来广播，user用来实现p2p
        config.enableSimpleBroker("/topic","/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册两个STOMP的endpoint，分别用于广播和点对点
        registry.addEndpoint("/webServer").withSockJS();
        registry.addEndpoint("/queueServer").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {
                return new WebSocketHandlerDecorator(handler) {
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                        session.sendMessage(new TextMessage("你好"));
                        // 客户端与服务器端建立连接后，此处记录谁上线了
//                        String username = session.getPrincipal().getName();
                        log.info("online: " + session.getId());
                        log.info("getHandshakeHeaders: {}" , session.getUri());
                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        // 客户端与服务器端断开连接后，此处记录谁下线了
//                        String username = session.getPrincipal().getName();
                        userSessionService.delete(session.getId());
                        log.info("offline: " + session.getId());
                        super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        });
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String jwtToken = accessor.getFirstNativeHeader("Auth-Token");
                    if (null != jwtToken && !"".equals(jwtToken)) {
                        userSessionService.add(accessor.getSessionId(), jwtToken);
                        log.info("online：用户token：{}，用户sessionid：{}", jwtToken, accessor.getSessionId());
                    }
                }
//                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
//log.info("message:{}",accessor.getHeartbeat());
//                    if (null != accessor.getHeartbeat()) {
//                        log.info("下线，用户sessionid：{}", accessor.getSessionId());
////                        UserAuthenticationToken authToken = tokenService.retrieveUserAuthToken(jwtToken);
////                        SecurityContextHolder.getContext().setAuthentication(authToken);
////                        accessor.setUser(authToken);
//                    }
//                }
                return message;
            }
        });
    }
}
