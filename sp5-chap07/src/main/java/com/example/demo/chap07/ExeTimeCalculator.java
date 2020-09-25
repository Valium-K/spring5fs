package com.example.demo.chap07;

public class ExeTimeCalculator implements Calculator {

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public Long factorial(Long num) {

        Long start = System.nanoTime();
        Long result = delegate.factorial(num);
        Long end = System.nanoTime();

        System.out.printf("%s.factorial(%d) spend time: %d\n",
                delegate.getClass().getSimpleName(),
                num,
                (end - start));

        return result;
    }
}
