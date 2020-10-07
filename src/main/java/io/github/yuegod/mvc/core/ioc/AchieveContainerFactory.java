package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.aop.ProxyObjectFactory;
import io.github.yuegod.mvc.core.common.ContainerFactory;
import io.github.yuegod.mvc.core.common.Factory;

import java.util.Map;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description
 **/
public class AchieveContainerFactory extends AbstractContainer implements ContainerFactory {
    @Override
    public Object getInstance(String instanceName) {
        Object instance = getSingleton(instanceName);
        if (instance != null) {
            return instance;
        }
        //标记正在创建
        if (!creationObjectFlags.contains(instanceName)) {
            creationObjectFlags.add(instanceName);
        }
        //实例化
        instance = createInstance(instanceName);
        return null;
    }


    @Override
    public Object createInstance(String instanceName) {
        Class clazz = candidateCache.get(instanceName);
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (instance instanceof Factory) {
            Factory factory = (Factory) instance;
            instance = factory.getObject();
        }
        return instance;
    }

    private Object getSingleton(String instanceName) {
        Object instance = singletonCache.get(instanceName);
        //如果一级缓存当中没有，并且还有在循环依赖标识当中存在，说明是循环依赖
        if (instance == null && creationObjectFlags.contains(instanceName)) {
            //如果二级缓存当中有，就直接返回二级缓存当中的
            if (incompleteInstanceCache.containsKey(instanceName)) {
                return incompleteInstanceCache.get(instanceName);
            }
            //如果是循环依赖先去三级缓存当中创建动态代理,从三级缓存当中拿
            ProxyObjectFactory objectFactory = proxyInstanceCache.get(instanceName);
            if (objectFactory != null) {
                Object factoryObject = objectFactory.getObject();
                incompleteInstanceCache.put(instanceName, factoryObject);
            }
        }
        return instance;
    }


}
