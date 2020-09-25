package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/*
    AOP 프로그래밍은 크게 @Aspect - @Pointcut - Advice(@Around 등등)이 큰 축이다.
    @Aspect는 공통기능을 위해,
    @Pointcut은 AOP의 적용 범위,
    Advice는 공통기능의 시점인 Before, After의 시점이다.
 */

// 공통기능 구현클래스 선언
@Aspect
@Order(2)
public class ExeTimeAspect {

    // Pointcut 설정을 해당패키지 및 하위의 public 메소드에 적용할 것이다.
    @Pointcut("execution(public * com.example.demo.chap07..*(..))")
    private void publicTarget() {}

    // Pointcut 설정을 해당패키지 및 하위의 protected 메소드에 적용할 것이다.
    @Pointcut("execution(protected * com.example.demo.chap07..*(..))")
    private void privateTarget() {}

    // Around Advice를 적용할 대상을 publicTarget()으로 설정
    // Advice를 적용할 대상의 함수이름은 @Pointcut의 설정의 이름이라 생각하면 된다.
    // Advice 대상 클래스의 기능을 AspectJ가 처리 후 ProceedingJointPoint 객체로 넘겨준다.
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        try {
            Object result = joinPoint.proceed();
            return result;
        }
        finally {
            long finish = System.nanoTime();

            // 호출되는 메소드의 정보를 갖고 있음.
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) spend time: %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }
}
