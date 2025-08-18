package com.ratanak.demo2.exception.model;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException(String message){
        super(message);

    }
}
