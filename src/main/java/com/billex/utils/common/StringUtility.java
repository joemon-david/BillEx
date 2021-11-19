package com.billex.utils.common;

import java.util.Arrays;

public  class StringUtility {

    public static String[] removeNullAndEmpty(String []stringArray)
    {
        String[] removedNull = Arrays.stream(stringArray)
                .filter(value ->
                        value != null && value.length() > 0
                )
                .toArray(size -> new String[size]);
        return removedNull;
    }
}
