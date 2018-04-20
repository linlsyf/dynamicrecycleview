package com.core.recycleview.base;

import java.math.BigDecimal;

public class MathsUtil {

    public static int retainDecimal(float value) {
        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static float retainDecimal(int decimalNumber, float value) {
        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(decimalNumber, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static double retainDecimal(int decimalNumber, double value) {
        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(decimalNumber, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
