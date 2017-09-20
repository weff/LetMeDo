package com.jtsoft.letmedo.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    public static boolean isMobileNO(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return false;
        } else if (!mobile.matches("\\d{11}")) {
            return false;
        }
        return true;

    }

    public static boolean isValidUserName(String username) {
        if (TextUtils.isEmpty(username)) {
            return false;
        }
        return true;
    }

    public static boolean isPwd(String pwd) {
        if (TextUtils.isEmpty(pwd) || pwd.length() > 15
                || pwd.length() < 6) {
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumber(String paramString) {
        String value = paramString;
        String regExp = "^13[0-9]{1}[0-9]{8}$|15[0125689]{1}[0-9]{8}$|18[0-3,5-9]{1}[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}

