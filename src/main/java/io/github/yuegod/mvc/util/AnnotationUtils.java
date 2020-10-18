package io.github.yuegod.mvc.util;

import java.lang.annotation.Annotation;

/**
 * @author quziwei
 * @date 2020/10/10
 * @description 注解工具类
 **/
public class AnnotationUtils {

    public static boolean isExistAnnotation(Class targetClazz, Class<? extends Annotation> annotation){
        if (targetClazz.getAnnotation(annotation) != null){
            return true;
        }
        return false;
    }

}
