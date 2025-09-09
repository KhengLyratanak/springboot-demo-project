package com.ratanak.demo2.exception.model;

public class UnprocessableEntityException extends RuntimeException {
    public UnprocessableEntityException (String message){
        super(message);
    }
}
