package com.example.demo.chap07;

public class RecCalculator implements Calculator{

    @Override
    public Long factorial(Long num) {
        if(num <= 0) return 1l;
        else return num * factorial(num - 1);
    }
}
