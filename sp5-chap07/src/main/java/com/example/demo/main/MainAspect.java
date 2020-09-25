package com.example.demo.main;

import com.example.demo.chap07.Calculator;
import com.example.demo.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        // 실제로 받은 객체는 Calculator 타입의 프록시 객체이다.
        Calculator cal = ctx.getBean("calculator", Calculator.class);

        long fiveFact = cal.factorial(5l);
        System.out.println("cal.factorial(5) = " + fiveFact);

        // 찍어보면 cal은 ...chap07.RecCalculator가 아닌
        // com.sun.proxy.$Proxy# 이다.
        System.out.println(cal.getClass().getName());

        ctx.close();
    }
}
