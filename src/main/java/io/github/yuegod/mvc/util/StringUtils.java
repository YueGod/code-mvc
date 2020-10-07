package io.github.yuegod.mvc.util;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description
 **/
public class StringUtils {

    public static boolean isEmpty(String s) {
        return (s == null || "".equals(s) || "".equals(s.trim()));
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
