package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(1)
public class CacheAspect {

    private Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(public * com.example.demo.chap07..*(Long))")
    public void cacheTarget() {}

    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Long num = (Long) proceedingJoinPoint.getArgs()[0];

        if(cache.containsKey(num)) {
            System.out.println("cache found: " + num);
            return cache.get(num);
        }

        Object result = proceedingJoinPoint.proceed();
        cache.put(num, result);
        System.out.println("Added into cache: " + num);

        return result;
    }
}
