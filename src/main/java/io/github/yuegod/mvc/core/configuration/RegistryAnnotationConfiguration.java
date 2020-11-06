package io.github.yuegod.mvc.core.configuration;

import io.github.yuegod.mvc.core.annotation.Registry;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 对于Registry注解的处理
 **/
public class RegistryAnnotationConfiguration {


    /**
     * 获取加了Registry注解的类型,并且按Order对注册类进行排序
     *
     * @return 返回一个排好序的RegistryDescription集合，改类型是对Registry类的一个描述
     */
    public Set<RegistryDescription> getRegistryClazz(Set<Class<?>> scannedClass) {
        Set<RegistryDescription> registryDescriptions = new LinkedHashSet<>();
        for (Class<?> clazz : scannedClass) {
            if (isRegistry(clazz)) {
                Registry registry = clazz.getAnnotation(Registry.class);
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
    private boolean isRegistry(Class<?> clazz) {
        return clazz.getAnnotation(Registry.class) != null;
    }

}
