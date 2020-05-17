package com.jaxer.doc.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created on 2020/5/16 17:50
 * AOP XML 配置切面
 *
 * @author jaxer
 */
public class ServiceMonitor {
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
