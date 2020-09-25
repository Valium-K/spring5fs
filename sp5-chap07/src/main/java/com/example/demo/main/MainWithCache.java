package com.example.demo.main;

import com.example.demo.aspect.CacheAspect;
import com.example.demo.chap07.Calculator;
import com.example.demo.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainWithCache {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator calculator = ctx.getBean("calculator", Calculator.class);

        // CacheAspect, ExeTimeAspect 중 어느것이 먼저 호출될지는
        // 자바, 스프링 버전에 따라 다르다.
        // 만약 순서가 중요하다면 @Aspect아래에 @Order(#)로 순서를 정해준다.
        calculator.factorial(7l);
        calculator.factorial(7l);
        calculator.factorial(5l);
        calculator.factorial(5l);

        ctx.close();
    }
}
