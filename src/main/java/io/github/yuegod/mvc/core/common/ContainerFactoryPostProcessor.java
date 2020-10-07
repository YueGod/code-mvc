package io.github.yuegod.mvc.core.common;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 容器工程后置处理器
 **/
public interface ContainerFactoryPostProcessor {

    /**
     * 在容器对类做实例化处理之前的一些方法
     * @param clazz 被扫描到的类
     * @param instanceName 实例称
     * @return 返回一个处理好的对象
     */
    Object ContaineriFactoryBeforePostProcessor(String instanceName,Class clazz);

}
