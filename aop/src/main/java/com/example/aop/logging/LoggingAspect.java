package com.example.aop.logging;

import java.util.Arrays;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component // Spring은 @Aspect를 컴포넌트로 취급하지 않으므로 Spring에서 관리하고 컴포넌트 스캐닝을 통해 감지해야 하는 빈임을 나타내기 위해 추가
public class LoggingAspect {

  private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

  @Pointcut("execution(public * com.example.aop.logging.*.*(..))")
  private void publicMethodsFromLoggingPackage() {
  }

  @Around(value = "publicMethodsFromLoggingPackage()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();
    String methodName = joinPoint.getSignature().getName();
    logger.info(">> {" + methodName + "}() - {" + Arrays.toString(args) + "}");
    Object result = joinPoint.proceed();
    logger.info("<< {" + methodName + "}() - {" + result + "}");
    return result;
  }
}