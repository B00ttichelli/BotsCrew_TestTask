package com.example.botscrew_testtask.exception;

public interface CustomFn<A, T> {
    T apply(A a) throws Exception;
}