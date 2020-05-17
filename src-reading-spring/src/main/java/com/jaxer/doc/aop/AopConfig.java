package com.jaxer.doc.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created on 2020/5/16 15:13
 *
 * @author jaxer
 */
@Configuration
@ComponentScan("com.jaxer.doc.aop.annotation")
@EnableAspectJAutoProxy
public class AopConfig {
}
