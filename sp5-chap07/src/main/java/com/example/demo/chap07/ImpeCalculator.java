package com.example.demo.chap07;

public class ImpeCalculator implements Calculator{

    @Override
    public Long factorial(Long num) {
        Long result = 1l;

        for(int i = 1; i <=num; i++) {
            result *= i;
        }
        return result;
    }
}
