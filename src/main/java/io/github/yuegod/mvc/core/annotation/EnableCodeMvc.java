package io.github.yuegod.mvc.core.annotation;

import java.lang.annotation.*;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description 启动Codemvc
 **/
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCodeMvc {
    String scanPackage() default "";
}
