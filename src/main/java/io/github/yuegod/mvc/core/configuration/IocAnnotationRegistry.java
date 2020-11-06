package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.core.annotation.IoC;
import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.*;
import io.github.yuegod.mvc.core.ioc.AchieveContainerFactory;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description IoC注解方法逻辑处理
 **/
@Registry(order = 0)
public class IocAnnotationRegistry implements AnnotationRegistry {

    @Override
    public Class<? extends Annotation> registry() {
        return IoC.class;
    }

    @Override
    public void handler(Class<?> clazz, AchieveContainerFactory containerFactory) {
        clazz = invokeContainerFactoryBeforePostProcessor(clazz.getName(),clazz,containerFactory);
        Object instance = createInstance(clazz);
        instance = invokeContainerFactoryAfterPostProcessor(clazz.getName(), instance,containerFactory);
        containerFactory.putIncompleteInstanceCache(clazz.getName(),instance);
    }

    /**
     * 实例化之前先调用一次ContainerFactoryPostProcessor
     */
    public Class<?> invokeContainerFactoryBeforePostProcessor(String instanceName,Class<?> clazz,AchieveContainerFactory containerFactory){
        Map<String, Object> registrySingletonCache = containerFactory.getRegistrySingletonCache();
        Set<Map.Entry<String, Object>> entries = registrySingletonCache.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getValue() instanceof ContainerFactoryPostProcessor){
                ContainerFactoryPostProcessor containerFactoryPostProcessor = (ContainerFactoryPostProcessor) entry.getValue();
                clazz = containerFactoryPostProcessor.containerFactoryBeforePostProcessor(instanceName, clazz);
            }
        }
        return clazz;
    }

    /**
     * 实例化
     */
    public Object createInstance(Class<?> clazz){
        try {
            Object instance = clazz.newInstance();
            if (instance instanceof FactoryInstance){
                FactoryInstance factory = (FactoryInstance) instance;
                instance = factory.getObject();
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 实例化之后再调用一次ContainerFactoryPostProcessor
     */
    public Object invokeContainerFactoryAfterPostProcessor(String instanceName,Object instance,AchieveContainerFactory containerFactory){
        Map<String, Object> registrySingletonCache = containerFactory.getRegistrySingletonCache();
        Set<Map.Entry<String, Object>> entries = registrySingletonCache.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getValue() instanceof ContainerFactoryPostProcessor){
                ContainerFactoryPostProcessor containerFactoryPostProcessor = (ContainerFactoryPostProcessor) entry.getValue();
                instance = containerFactoryPostProcessor.containerFactoryAfterPostProcessor(instanceName, instance);
            }
        }
        return instance;
    }
}
