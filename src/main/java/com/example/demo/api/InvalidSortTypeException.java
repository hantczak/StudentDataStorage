package com.example.demo.api;

public class InvalidSortTypeException extends RuntimeException{
    public InvalidSortTypeException(String message){
        super("Invalid sort parameter " + message);
    }
}
