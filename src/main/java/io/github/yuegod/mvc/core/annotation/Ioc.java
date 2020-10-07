package io.github.yuegod.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 将对象交给容器管理
 **/
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Ioc {

    /**
     * 是否为单例模式
     */
    boolean singleton() default true;

    /**
     * 是否为懒加载
     */
    boolean lazy() default false;
}
