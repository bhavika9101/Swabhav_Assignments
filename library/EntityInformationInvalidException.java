package com.tss.library;

public class EntityInformationInvalidException extends Exception{
    String message;
    public EntityInformationInvalidException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
