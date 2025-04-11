package org.wqz.whitelistspringbootstarter.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.wqz.whitelistspringbootstarter.annotation.Whitelist;

import java.lang.reflect.Method;

/**
 * @Description: 白名单拦截切面
 * @Author: wjh
 * @Date: 2025/4/11 上午8:19
 */

@Aspect
@Component
public class WhitelistAspect {

    @Before("@annotation(org.wqz.whitelistspringbootstarter.annotation.Whitelist)")
    public void checkWhitelist(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Whitelist whitelist = method.getAnnotation(Whitelist.class);
        String[] allowedIps = whitelist.value();

        // 这里假设获取客户端 IP 的方法
        String clientIp = getClientIp();

        boolean isAllowed = false;
        for (String allowedIp : allowedIps) {
            if (allowedIp.equals(clientIp)) {
                isAllowed = true;
                break;
            }
        }

        if (!isAllowed) {
            throw new RuntimeException("IP not in whitelist");
        }
    }

    private String getClientIp() {
        // 实际应用中需要实现获取客户端 IP 的逻辑
        return "127.0.0.1";
    }
}
