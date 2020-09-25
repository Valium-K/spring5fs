package com.example.demo.config;

import com.example.demo.spring.Client;
import com.example.demo.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx {

    // client는 Client 클래스에서 직접
    // destory()와 afterPropertiesSet()을 구현함
    // 주의점은 두 함수는 객체생성시에 자동으로 호출되기에 아래에서 함수를 또 호출하면 안됨.
    @Bean
    // 범위가 프로토타입일 경우 매번 새로운 객체 생성
    // 프로토타입은 스프링이 소멸처리를 하지않음으로 직접 처리해야함
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    // client2는 @Bean의 속성값을 이용해 필요한 함수를 수행함.
    // 여기서 connect와 close는 함수의 이름이다.
    @Bean(initMethod = "connect", destroyMethod = "close")
    // 빈의 스코프는 기본적으로 싱글톤이지만 명시적으로 알리고싶으면 아래코드를 사용
    @Scope("singleton")
    public Client2 client2() {
        Client2 client2 = new Client2();
        client2.setHost("host2");
        return client2;
    }
}
