package customAnnotation;

import java.math.BigDecimal;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/1/6 18:38
 * @Version 1.1
 */
public class CommonUtils {

    private static final int DEFAULT_DIGITS = 2;
    private static final int DEFAULT_ROUND = BigDecimal.ROUND_HALF_UP;

    public static BigDecimal getBigDecimal(BigDecimal b){
        return b.setScale(DEFAULT_DIGITS, DEFAULT_ROUND);
    }

    public static double getDoubleRound(double decimal){
        return getDoubleRound(decimal, DEFAULT_DIGITS);
    }

    public static double getDoubleRound(double decimal, int digits){
        return getDoubleRound(decimal, digits, DEFAULT_ROUND);
    }

    public static double getDoubleRound(double decimal, int digits, int round){
        return new BigDecimal(decimal).setScale(digits, round).doubleValue();
    }
}