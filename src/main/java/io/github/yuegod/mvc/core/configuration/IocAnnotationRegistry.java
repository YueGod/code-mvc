package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.core.annotation.IoC;
import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.AnnotationRegistry;
import io.github.yuegod.mvc.core.ioc.AchieveContainerFactory;

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
        return IoC.class;
    }

    @Override
    public void handler(Class clazz, AchieveContainerFactory containerFactory) {

    }
}
