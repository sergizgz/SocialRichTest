package com.socialrich.socialrich.exceptions;

import com.socialrich.socialrich.constants.Constants;

public class NoUserException extends BaseException {

    private static final String CODEERROR= Constants.CODE_NO_USER_EXCEPTION;//usuario no encontrado
    public NoUserException(String message) {

        super(message);
    }

    public static String getCodeError() {
        return CODEERROR;
    }
}
