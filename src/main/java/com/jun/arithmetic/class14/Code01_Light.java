package com.jun.arithmetic.class14;

/**
 * @decription: 路灯问题
 * @date: 2022/7/20 17:07
 * @author: longjunjie@xinpayroll.com
 * @Since:
 */
public class Code01_Light {

    // todo minLight1方法待补充


    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int light = 0;
        int i = 0;
        while (i < str.length) {
            if ( str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length ) {
                    break;
                } else  {
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

}
