package com.socialrich.socialrich.exceptions;

public class BaseException extends Exception{

    private String code;
    public BaseException (String code,String message){

        super(message);
    }


}
