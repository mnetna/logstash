package com.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Around("execution(* com.test.*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        String value = UUID.randomUUID().toString();
        MDC.put("guid", value);
//        logger.info("Method Start : " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        return result;
    }
}
