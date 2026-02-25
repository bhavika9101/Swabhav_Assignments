package com.tss.exception;

public class CapacityFullException extends Exception{
    int capacity;

    public CapacityFullException(int capacity){
        this.capacity = capacity;
    }
    @Override
    public String getMessage() {
        return "CapacityFullException: Capacity full. Can't add " + capacity +"th object.";
    }
}
