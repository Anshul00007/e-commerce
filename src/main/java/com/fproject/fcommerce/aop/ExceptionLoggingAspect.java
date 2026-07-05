package com.fproject.fcommerce.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @AfterThrowing(
            pointcut = "execution(* com.fproject.fcommerce.service.*.*(..))",
            throwing = "ex")
    public void logException(Exception ex) {

        log.error("========== EXCEPTION ==========");
        log.error("Exception Type : {}", ex.getClass().getSimpleName());
        log.error("Message : {}", ex.getMessage());
        log.error("===============================");
    }
}