package io.github.yuegod.mvc.aop;

import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.ContainerPostProcessor;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description 获取AOP
 **/
@Registry
public class ObtainDynamicProxyPostProcessor implements ContainerPostProcessor {
    @Override
    public Object containerBeforePostProcessor(String instanceName, Object instance) {
        return instance;
    }
}
