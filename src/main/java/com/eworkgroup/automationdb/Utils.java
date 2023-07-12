package com.eworkgroup.automationdb;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public String parse(String s) {
        return s.length() > 255 ? s.substring(0, 255).trim() : s.trim();
    }
}
