package com.api.bigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;

//        方式1. 将两个数据包装成 BigDecimal 对象
//        BigDecimal a1 = new BigDecimal(Double.toString(a));
//        BigDecimal b1 = new BigDecimal(Double.toString(b));

//        方式2. 阿里更推荐使用 valueOf 包装浮点型整数成为 BigDecimal 对象
        BigDecimal a11 = BigDecimal.valueOf(a);
        BigDecimal b11 = BigDecimal.valueOf(b);

        BigDecimal result = a11.add(b11);
        double res = result.doubleValue();
        System.out.println(result);

        System.out.println("-----------------------------");
        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);
        BigDecimal k = i.divide(j, 2, RoundingMode.HALF_UP);
        System.out.println(k.doubleValue());
    }
}
