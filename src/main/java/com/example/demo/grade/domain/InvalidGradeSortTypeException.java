package com.example.demo.grade.domain;

public class InvalidGradeSortTypeException extends RuntimeException{
    InvalidGradeSortTypeException(String message){
        super("Invalid sort parameter " + message);
    }
}
