package com.duan.video.aop;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.duan.video.pojo.entity.RequestLog;
import com.duan.video.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class RequestAop {
    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.duan.video.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Date startTime = DateUtil.date();
        Object proceed = joinPoint.proceed();
        Date endTime = DateUtil.date();
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURI();
            String method = request.getMethod();
            //获取GET请求参数
            String queryString = request.getQueryString();
            long timeDiff = Integer.parseInt(String.valueOf(DateUtil.between(startTime, endTime, DateUnit.MS)));
            RequestLog requestLog = new RequestLog();
            requestLog.setIp(IpUtil.getIpAddr(request)).setUrl(url).setHttpMethod(method).setClassName(joinPoint.getSignature().getDeclaringTypeName()).setMethod(joinPoint.getSignature().getName()).setParams(queryString).setStartTime(startTime).setEndTime(endTime).setTimeDiff(timeDiff);
            requestLog.insert();

        } catch (Exception e) {
            log.error("记录请求日志出现异常", e);
        }

        return proceed;
    }
}
