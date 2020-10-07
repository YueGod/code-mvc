package io.github.yuegod.mvc.aop;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description
 **/
@FunctionalInterface
public interface ProxyObjectFactory<T> {

    /**
     * 用于循环依赖AOP问题回调方法
     * @return 返回一个被动态代理的对象
     */
    T getObject();
}
