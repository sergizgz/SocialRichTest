package com.socialrich.socialrich.constants;

public final class Constants {

    public static final String NO_USER = "No user with this ID on database";
    public static final String NO_USERS = "No users on database";

    public static final String NO_RED_SOCIAL = "No red social on database";


    //CODIGOS DE ERROR
    public static final String CODE_NO_USER_EXCEPTION = "P-500";
    public static final String CODE_NO_RED_SOCIAL_EXCEPTION = "P-501";


    private Constants(){
        throw new IllegalStateException("Utility class");
    }
}
