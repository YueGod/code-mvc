package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.AnnotationRegistry;

import java.lang.annotation.Annotation;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description IoC注解方法逻辑处理
 **/
@Registry
public class IocAnnotationRegistry implements AnnotationRegistry {
    @Override
    public Class<? extends Annotation> registry() {
        return io.github.yuegod.mvc.annotation.Ioc.class;
    }

    @Override
    public void handler(Class clazz) {

    }
}
