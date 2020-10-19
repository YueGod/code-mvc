package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.aop.ProxyObjectFactory;
import io.github.yuegod.mvc.core.annotation.Injection;
import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.AnnotationRegistry;
import io.github.yuegod.mvc.core.ioc.AchieveContainerFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description 依赖注入注解注册类
 **/
@Registry(order = 2)
@Slf4j
public class InjectionAnnotationRegistry implements AnnotationRegistry {
    @Override
    public Class<? extends Annotation> registry() {
        return Injection.class;
    }

    @Override
    public void handler(Class clazz, AchieveContainerFactory containerFactory) {
        Set<Map.Entry<String, Object>> entries = containerFactory.getIncompleteInstanceCache().entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            try {
                Object instance = getInstance(entry.getKey(), entry.getValue(), containerFactory);
                containerFactory.getSingletonCache().put(entry.getKey(), instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Object getInstance(String instanceName, Object instance, AchieveContainerFactory containerFactory) throws Exception {
        Object sigleton = getSingleton(instanceName, containerFactory);
        if (sigleton != null) {
            return sigleton;
        }
        synchronized (containerFactory.getIncompleteInstanceCache()) {
            //标记正在创建
            if (!containerFactory.getCircularDependencyFlags().contains(instanceName)) {
                containerFactory.getCircularDependencyFlags().add(instanceName);
            }
            //属性赋值
            try {
                Field[] declaredFields = instance.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    try {
                        Injection annotation = declaredField.getAnnotation(Injection.class);
                        if (annotation != null) {
                            declaredField.setAccessible(true);
                            Object fieldInstance = containerFactory.getIncompleteInstanceCache().get(declaredField.getType().getName());
                            Object bean = getInstance(declaredField.getType().getName(), fieldInstance, containerFactory);
                            declaredField.set(instance, bean);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("属性赋值出错！欲赋值的类:{},欲赋值字段{}", instance.getClass().getName(), declaredField.getName());
                    }
                }
            } catch (Exception e) {
                log.error("属性赋值出错！欲赋值的类:{}", instance.getClass().getName());

            }
            //要先从二级缓存当中拿一下
            if (containerFactory.getIncompleteInstanceCache().containsKey(instanceName)) {
                instance = containerFactory.getIncompleteInstanceCache().get(instanceName);
            }
            //这时候我赋值进去的A就是一个动态代理的A
            //添加到一级缓存
            containerFactory.getSingletonCache().put(instanceName, instance);
        }

        //remove 二级缓存和三级缓存
        return instance;
    }

    private Object getSingleton(String instanceName, AchieveContainerFactory containerFactory) {
        Object bean = containerFactory.getSingletonCache().get(instanceName);
        synchronized (containerFactory.getIncompleteInstanceCache()) {
            //如果一级缓存当中没有，并且还有在循环依赖标识当中存在，说明是循环依赖
            if (bean == null && containerFactory.getCircularDependencyFlags().contains(instanceName)) {
                //如果二级缓存当中有，就直接返回二级缓存当中的
                if (containerFactory.getIncompleteInstanceCache().containsKey(instanceName)) {
                    return containerFactory.getIncompleteInstanceCache().get(instanceName);
                }
                //如果是循环依赖先去三级缓存当中创建动态代理,从三级缓存当中拿
                ProxyObjectFactory objectFactory = containerFactory.getProxyInstanceCache().get(instanceName);
                if (objectFactory != null) {
                    Object factoryObject = objectFactory.getObject();
                    containerFactory.getIncompleteInstanceCache().put(instanceName, factoryObject);
                }
            }
        }
        return bean;
    }
}
