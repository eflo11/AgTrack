package com.agtrack.utilities;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

class Authorization {

    public static String decodeProfile(String authorization) throws UnsupportedEncodingException {
        return decode(authorization.substring(6))[0];
    }

    public static String decodePassword(String authorization) throws UnsupportedEncodingException {
        return decode(authorization.substring(6))[1];
    }

    private static String[] decode(String encoded) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(encoded), "utf-8").split(":");
    }


}
