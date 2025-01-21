package com.example.iocexam.aop;

import java.util.Arrays;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());


  @Pointcut("execution(public * com.example.iocexam.controller.*.*(..))")
  private void publicMethodsFromLoggingPackage() {
  }

  @Around(value = "publicMethodsFromLoggingPackage()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getName();
    logger.info(">> " + methodName + "() - " + Arrays.toString(args));
    Object result = joinPoint.proceed();
    logger.info("<< " + methodName + "() - " + result);
    return result;
  }

}
