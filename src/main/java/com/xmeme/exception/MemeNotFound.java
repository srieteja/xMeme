package com.xmeme.exception;

public class MemeNotFound extends RuntimeException{
    final private static String errorMessage = "Meme Does Not Exist";

        public MemeNotFound(){
            super(errorMessage);
        }
}
