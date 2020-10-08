package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.AnnotationRegistry;
import io.github.yuegod.mvc.core.ioc.ContainerHandler;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 对于Registry注解的处理
 **/
public class RegistryAnnotationConfiguration {


    /**
     * 获取加了Registry注解的类型
     *
     * @return 返回集合
     */
    public Set<RegistryDescription> getRegistryClazz(Set<Class> scannedClass) {
        Set<RegistryDescription> registryDescriptions = new LinkedHashSet<>();
        for (Class clazz : scannedClass) {
            if (isRegistry(clazz)) {
                Registry registry = (Registry) clazz.getAnnotation(Registry.class);
                RegistryDescription registryDescription = new RegistryDescription(clazz.getName(), clazz, registry.order());
                registryDescriptions.add(registryDescription);
            }
        }
        //按Order进行排序
        registryDescriptions.stream().sorted(Comparator.comparingInt(RegistryDescription::getOrder));
        return registryDescriptions;
    }

    /**
     * 判断该类是否加了Registry注解
     */
    private boolean isRegistry(Class clazz) {
        if (clazz.getAnnotation(Registry.class) != null) {
            return true;
        }
        return false;
    }

}
