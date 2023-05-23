package com.socialrich.socialrich.exceptions;

import com.socialrich.socialrich.constants.Constants;

public class NoUserException extends BaseException {

    private static String codeError= Constants.CODE_NO_USER_EXCEPTION;//usuario no encontrado
    public NoUserException(String message) {
        super(codeError,message);
    }

    public static String getCodeError() {
        return codeError;
    }
}
