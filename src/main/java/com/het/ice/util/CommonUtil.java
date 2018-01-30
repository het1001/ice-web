package com.het.ice.util;

import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 */
public class CommonUtil {

    private static final String OS_KEY = "os.name";

    private static final String WIN = "win";

    private static final long SECOND = 1000;

    private static final long MINUTE = 60 * SECOND;

    private static final long HOUR = 60 * MINUTE;

    private static final long DAY = 24 * HOUR;

    public static boolean isWinOS() {
        String os = System.getProperty(OS_KEY);
        return os.toLowerCase().startsWith("win");
    }

    public static String parseTime(long time) {
        StringBuffer result = new StringBuffer();

        if (time / DAY > 0) {
            result.append(time / DAY).append("天");
        }

        if (time % DAY / HOUR > 0) {
            result.append(time % DAY / HOUR).append("小时");
        }

        if (time % DAY % HOUR / MINUTE > 0) {
            result.append(time % DAY % HOUR / MINUTE).append("分");
        }

        if (StringUtils.isEmpty(result.toString())) {
            if (time % DAY % HOUR % MINUTE / SECOND > 0) {
                result.append(time % DAY % HOUR % MINUTE / SECOND).append("分");
            }
        }

        return result.toString();
    }
}
