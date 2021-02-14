package com.xmeme.exception;

public class ImproperConstraintException extends RuntimeException{
    private final static  String errorMessage = "The request parameters are improper or do not satisfy requirements";

    public ImproperConstraintException(){
        super(errorMessage);
    }
}
