package com.hospital.common.util;

public class MaskingUtil {

    private MaskingUtil() {}

    public static String maskEmail(
            String email) {

        if (email == null
                || !email.contains("@")) {

            return "****";
        }

        int atIndex =
                email.indexOf("@");

        return email.substring(0, 2)
                + "****"
                + email.substring(atIndex);
    }

    public static String maskMobile(
            String mobile) {

        if (mobile == null
                || mobile.length() < 4) {

            return "****";
        }

        return "******"
                + mobile.substring(
                        mobile.length() - 4
                );
    }
}