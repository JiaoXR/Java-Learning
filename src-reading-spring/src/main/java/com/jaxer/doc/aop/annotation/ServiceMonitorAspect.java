package com.jaxer.doc.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created on 2020/5/16 15:03
 * AOP 注解配置切面
 *
 * @author jaxer
 */
@Aspect
@Component
public class ServiceMonitorAspect {

    @Pointcut("execution(* com.jaxer.doc.aop.UserDAO.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + " 毫秒");
        return proceed;
    }
}
