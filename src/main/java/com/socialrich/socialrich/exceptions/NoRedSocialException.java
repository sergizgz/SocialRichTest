package com.socialrich.socialrich.exceptions;

import com.socialrich.socialrich.constants.Constants;

public class NoRedSocialException extends BaseException {

    private static final String CODEERROR= Constants.CODE_NO_RED_SOCIAL_EXCEPTION;//codigo red social no encontrado
    private static final String MENSAJE= Constants.NO_RED_SOCIAL;//red social  no encontrado
    public NoRedSocialException() {
        super(MENSAJE);
    }

    public static String getCodeError() {
        return CODEERROR;
    }

    public static String getMensaje() {
        return MENSAJE;
    }
}
