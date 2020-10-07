package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.aop.ProxyObjectFactory;
import io.github.yuegod.mvc.core.common.ContainerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 用于定义一些方法和公用成员变量
 **/
public abstract class AbstractContainer {

    /**
     * 将扫描到的包路径下的类缓存到集合，等待处理完毕后再清空
     */
    public Set<Class> scannedClazz = new HashSet<>();

    /**
     * 扫描到的候选类的缓存
     */
    protected Map<String, Class> candidateCache = new ConcurrentHashMap<>(256);

    /**
     * 单例缓存池
     */
    protected Map<String, Object> singletonCache = new ConcurrentHashMap<>(128);

    /**
     * 二级缓存池，用于解决循环依赖问题
     */
    protected Map<String, Object> incompleteInstanceCache = new ConcurrentHashMap<>();

    /**
     * 三级缓存池，解决循环依赖中动态代理问题
     */
    protected Map<String, ProxyObjectFactory> proxyInstanceCache = new ConcurrentHashMap<>();

    /**
     * 用于标记正在创建的对象的标记
     */
    protected Set<String> creationObjectFlags = new HashSet<>(128);

    /**
     * 注解注册器
     */
    protected Map<String, Class<? extends Annotation>> annotationRegistryCache = new ConcurrentHashMap<>(32);


}
