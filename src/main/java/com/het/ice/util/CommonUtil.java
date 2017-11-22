package com.het.ice.util;

/**
 *
 *
 */
public class CommonUtil {

    private static final String OS_KEY = "os.name";

    private static final String WIN = "win";

    public static boolean isWinOS() {
        String os = System.getProperty(OS_KEY);
        return os.toLowerCase().startsWith("win");
    }
}
