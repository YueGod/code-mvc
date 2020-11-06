package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.aop.ProxyObjectFactory;
import io.github.yuegod.mvc.core.common.ContainerFactory;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 **/
public class AchieveContainerFactory extends AbstractContainer implements ContainerFactory {
    @Override
    public Object getInstance(String instanceName) {
        return singletonCache.get(instanceName);
    }

    public  <T> T getInstance(String instanceName,Class<?> clazz){
        return (T) clazz.cast(getInstance(instanceName));
    }

    public Map<String, Object> getSingletonCache() {
        return singletonCache;
    }

    public void putSingletonCache(String instanceName, Object instance) {
        singletonCache.put(instanceName, instance);
    }

    public Set<Class<?>> getScannedClazz() {
        return scannedClazz;
    }

    public void putScannedClazz(Class<?> clazz) {
        scannedClazz.add(clazz);
    }

    public Map<String, Class<?>> getCandidateCache() {
        return candidateCache;
    }

    public void putCandidateCache(String instanceName, Class<?> clazz) {
        candidateCache.put(instanceName, clazz);
    }

    public Map<String, Object> getIncompleteInstanceCache() {
        return incompleteInstanceCache;
    }

    public void putIncompleteInstanceCache(String instanceName, Object instance) {
        incompleteInstanceCache.put(instanceName, instance);
    }

    public Map<String, ProxyObjectFactory<?>> getProxyInstanceCache() {
        return proxyInstanceCache;
    }

    public void putProxyInstanceCache(String instanceName, ProxyObjectFactory<?> proxyObjectFactory) {
        proxyInstanceCache.put(instanceName, proxyObjectFactory);
    }

    public Set<String> getCircularDependencyFlags() {
        return circularDependencyFlags;
    }

    public void putCircularDependencyFlags(String instanceName) {
        circularDependencyFlags.add(instanceName);
    }

    public Map<String, Class<? extends Annotation>> getAnnotationRegistryCache() {
        return annotationRegistryCache;
    }

    public void putAnnotationRegistryCache(String instanceName, Class<? extends Annotation> annotationClazz) {
        annotationRegistryCache.put(instanceName, annotationClazz);
    }

    public Map<String, Object> getRegistrySingletonCache() {
        return registrySingletonCache;
    }

    public void putRegistrySingletonCache(String instanceName, Object instance) {
        registrySingletonCache.put(instanceName, instance);
    }

}
