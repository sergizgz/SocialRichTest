package com.socialrich.socialrich.exceptions;

import com.socialrich.socialrich.constants.Constants;

public class NoUserException extends BaseException {

    private static final String CODEERROR= Constants.CODE_NO_USER_EXCEPTION;//codigo usuario no encontrado
    private static final String MENSAJE= Constants.NO_USER;//usuario no encontrado

    public NoUserException() {
        super(MENSAJE);
    }

    public static String getCodeError() {

        return CODEERROR;
    }

    public static String getMensaje() {

        return MENSAJE;
    }
}
