package com.example.demo.spring;


public class Client2 {

    private String host;

    public void connect() {
        System.out.println("Client2.connect() started.");
    }
    public void send() {
        System.out.println("Client2.send() to " + host);

    }

    public void close() {
        System.out.println("Client2.close() started...");
    }
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
