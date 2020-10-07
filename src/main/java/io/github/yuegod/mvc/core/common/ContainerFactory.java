package io.github.yuegod.mvc.core.common;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description
 **/
public interface ContainerFactory {
    
    Object getInstance(String instanceName);

    Object createInstance(String instanceName);
}
