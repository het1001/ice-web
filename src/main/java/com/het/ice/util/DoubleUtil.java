package com.het.ice.util;

import java.math.BigDecimal;

/**
 *
 */
public class DoubleUtil {

    /**
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double subtract(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        return multiply(v1 + "", v2 + "");
    }

    public static double multiply(String v1, String v2) {
        BigDecimal a1 = new BigDecimal(v1);
        BigDecimal b1 = new BigDecimal(v2);
        return a1.multiply(b1).doubleValue();
    }
}
