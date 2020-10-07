package io.github.yuegod.mvc.core.annotation;

import java.lang.annotation.*;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 注入
 **/
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Injection {
}
