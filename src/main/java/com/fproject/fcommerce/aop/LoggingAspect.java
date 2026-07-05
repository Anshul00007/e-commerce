package com.fproject.fcommerce.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.fproject.fcommerce.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        log.info("========== METHOD START ==========");
        log.info("Method : {}", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info("Method : {}", joinPoint.getSignature().getName());
        log.info("Execution Time : {} ms", (end - start));
        log.info("=========== METHOD END ===========");

        return result;
    }
}