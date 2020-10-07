package io.github.yuegod.mvc.core.common;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 容器后置处理器
 **/
public interface ContainerPostProcessor {

    /**
     * 容器在进行依赖注入前的拓展操作
     *
     * @param instanceName 名称
     * @param instance     bean实例
     * @return 处理后的实例对象
     */
    default Object containerBeforePostProcessor(String instanceName, Object instance) {
        return instance;
    }

    /**
     * 容器在进行依赖注入后的拓展操作
     *
     * @param instanceName 名称
     * @param instance     bean实例
     * @return 处理后的实例对象
     */
    default Object containerAfterPostProcessor(String instanceName, Object instance) {
        return instance;
    }

}
