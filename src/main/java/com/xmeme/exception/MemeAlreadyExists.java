package com.xmeme.exception;

public class MemeAlreadyExists extends RuntimeException{
    private final static String errorMessage= "Meme already Exists";

    public MemeAlreadyExists(){
        super(errorMessage);
    }
}
