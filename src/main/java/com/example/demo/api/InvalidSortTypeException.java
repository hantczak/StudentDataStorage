package com.example.demo.api;

import java.util.Arrays;

public class InvalidSortTypeException extends RuntimeException{
    public InvalidSortTypeException(String message){
        super("Invalid sort parameter " + message +". Available values: " + Arrays.toString(SortType.values()));
    }
}
