package io.github.yuegod.mvc.core.common;

import io.github.yuegod.mvc.core.ioc.AchieveContainerFactory;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 注解注册类
 **/
public interface AnnotationRegistry {
    /**
     * 注册一个自定义注解，并实现一个对该注解处理的方法
     *
     * @return 该注解的类的set集合
     */
    Class<? extends Annotation> registry();


    /**
     * 对于该注解的处理方法
     *
     * @param clazz 传入一个被扫描到的Class类
     */
    void handler(Class clazz, AchieveContainerFactory containerFactory);

}
