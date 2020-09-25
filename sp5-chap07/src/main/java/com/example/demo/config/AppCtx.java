package com.example.demo.config;

import com.example.demo.aspect.CacheAspect;
import com.example.demo.aspect.ExeTimeAspect;
import com.example.demo.chap07.Calculator;
import com.example.demo.chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// @Aspect를 붙인 클래스를 공통 기능으로 사용하기 위해 적용
// 속성을 비워드면 proxyTargetClass = false로, 인터페이스를 상속받아 프록시를 생성함
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {

    // 공통기능(Aspect) 클래스 등록
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    // 핵심기능 대상 클래스
    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }
}
