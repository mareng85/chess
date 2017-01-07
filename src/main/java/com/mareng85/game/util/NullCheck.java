package com.mareng85.game.util;

public class NullCheck {

    public static void check(String errorMessage, Object ... o) {
        for (Object obj : o) {
            if (obj == null) {
                throw new RuntimeException(errorMessage);
            }
        }
    }
}
