package com.example.demo.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {
    private String host;

    public void send() {
        System.out.println("Client.send() to " + host);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    // destory()를 구현해 destory시에 코드 실행
    @Override
    public void destroy() throws Exception {
        System.out.println("destory");
    }

    // init 시에 필요한 콛 구현
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
