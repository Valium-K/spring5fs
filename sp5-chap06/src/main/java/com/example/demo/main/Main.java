package com.example.demo.main;

import com.example.demo.config.AppCtx;
import com.example.demo.spring.Client;
import com.example.demo.spring.Client2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx.class);

        Client client = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        client.send();
        System.out.print("======== " + client.hashCode() + " " + client2.hashCode() + " =======");
        ctx.close();

    }
}
