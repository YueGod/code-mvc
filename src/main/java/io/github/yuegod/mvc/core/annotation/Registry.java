package io.github.yuegod.mvc.core.annotation;

import java.lang.annotation.*;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 注册一个配置给code-mvc
 **/
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Registry {
    /**
     * 排序 order越小优先级越高
     */
    int order() default 0;
}
