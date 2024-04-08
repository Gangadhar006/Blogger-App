package org.blogger.bloggerapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.blogger.bloggerapp.constants.LoggingConstants.*;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(LOGGING_POINTCUT_PATH)
    public void logging() {
    }

    @Before(LOGGING_POINTCUT_NAME)
    public void entryLog(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(METHOD_ENTRY, methodName, className);
    }

    @After(LOGGING_POINTCUT_NAME)
    public void exitLog(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info(METHOD_EXIT, methodName, className);
    }
}