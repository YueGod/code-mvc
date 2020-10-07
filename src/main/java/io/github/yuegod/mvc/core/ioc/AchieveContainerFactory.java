package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.aop.ProxyObjectFactory;
import io.github.yuegod.mvc.core.common.ContainerFactory;
import io.github.yuegod.mvc.core.common.Factory;

import java.lang.annotation.Annotation;
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

    public Map<String, Object> getSingletonCache() {
        return singletonCache;
    }

    public void putSingletonCache(String instanceName, Object instance) {
        singletonCache.put(instanceName, instance);
    }

    public Set<Class> getScannedClazz() {
        return scannedClazz;
    }

    public void putScannedClazz(Class clazz) {
        scannedClazz.add(clazz);
    }

    public Map<String, Class> getCandidateCache() {
        return candidateCache;
    }

    public void putCandidateCache(String instanceName, Class clazz) {
        candidateCache.put(instanceName, clazz);
    }

    public Map<String, Object> getIncompleteInstanceCache() {
        return incompleteInstanceCache;
    }

    public void putIncompleteInstanceCache(String instanceName, Object instance) {
        incompleteInstanceCache.put(instanceName, instance);
    }

    public Map<String, ProxyObjectFactory> getProxyInstanceCache() {
        return proxyInstanceCache;
    }

    public void putProxyInstanceCache(String instanceName, ProxyObjectFactory proxyObjectFactory) {
        proxyInstanceCache.put(instanceName, proxyObjectFactory);
    }

    public Set<String> getCreationObjectFlags() {
        return creationObjectFlags;
    }

    public void puttCreationObjectFlags(String instanceName) {
        creationObjectFlags.add(instanceName);
    }

    public Map<String, Class<? extends Annotation>> getAnnotationRegistryCache() {
        return annotationRegistryCache;
    }

    public void putAnnotationRegistryCache(String instanceName, Class<? extends Annotation> annotationClazz) {
        annotationRegistryCache.put(instanceName, annotationClazz);
    }

}
