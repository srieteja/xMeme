package com.xmeme;

import com.xmeme.exception.ImproperConstraintException;
import com.xmeme.exception.MemeAlreadyExists;
import com.xmeme.exception.MemeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemeNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMemeNotFound(MemeNotFound exception){
        return '{' + "message :"+exception.getMessage()+"}";
    }

    @ExceptionHandler(MemeAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleMemeExists(MemeAlreadyExists exception){
        return '{' + "message :"+exception.getMessage()+"}";
    }

    @ExceptionHandler(ImproperConstraintException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleImroperException(ImproperConstraintException exception){
        return '{' + "message :"+exception.getMessage()+"}";
    }

}
