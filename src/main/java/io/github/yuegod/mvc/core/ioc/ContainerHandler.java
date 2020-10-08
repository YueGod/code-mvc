package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.core.common.AnnotationRegistry;
import io.github.yuegod.mvc.core.configuration.RegistryAnnotationConfiguration;
import io.github.yuegod.mvc.core.configuration.RegistryDescription;
import io.github.yuegod.mvc.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 容器处理
 **/
public class ContainerHandler {

    private AchieveContainerFactory containerFactory;

    private RegistryAnnotationConfiguration registryAnnotationConfiguration;

    private HashMap<String, AnnotationRegistry> annotationRegistryHashMap;

    public ContainerHandler() {
        this(new AchieveContainerFactory());
    }

    public ContainerHandler(AchieveContainerFactory containerFactory) {
        this.containerFactory = containerFactory;
    }

    /**
     * 1.先扫描出所有的类并添加到对应的缓存容器中
     */
    private void scan(String packagePath) {
        ContainerScanner scanner = new ContainerScanner();
        if (StringUtils.isNotEmpty(packagePath)) {
            Set<Class<?>> classes = scanner.scan(packagePath);
            containerFactory.getScannedClazz().addAll(classes);
        } else {
            Set<Class<?>> classes = scanner.scan();
            containerFactory.getScannedClazz().addAll(classes);
        }

    }

    /**
     * 2.初始化RegistryAnnotationConfiguration
     */
    private void initializationRegistryAnnotationConfiguration() {
        this.registryAnnotationConfiguration = new RegistryAnnotationConfiguration();
    }

    /**
     * 3.执行RegistryAnnotationConfiguration，将扫描到的注册到容器中
     */
    private void registryConfiguration() {
        Set<RegistryDescription> registryDescriptions = registryAnnotationConfiguration.getRegistryClazz(containerFactory.getScannedClazz());
        for (RegistryDescription registryDescription : registryDescriptions) {
            try {
                containerFactory.putRegistrySingletonCache(registryDescription.getInstanceName(), registryDescription.getRegistryClazz().newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 4.找到带有实现AnnotationRegistry的对象并执行registry方法，向容器中注册该注解
     */
    private void invokeAnnotationRegistry() {
        HashMap<String, AnnotationRegistry> annotationRegistryHashMap = this.annotationRegistryHashMap;
        Set<Map.Entry<String, AnnotationRegistry>> entries = annotationRegistryHashMap.entrySet();
        for (Map.Entry<String, AnnotationRegistry> entry : entries) {
            AnnotationRegistry annotationRegistry = entry.getValue();
            containerFactory.putAnnotationRegistryCache(entry.getKey(), annotationRegistry.registry());
        }
    }

    /**
     * 5.找到符合AnnotationRegistry类型的实例
     */
    public void findAnnotationRegistryInstance() {
        HashMap<String, AnnotationRegistry> annotationRegistries = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = containerFactory.getRegistrySingletonCache().entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getValue() instanceof AnnotationRegistry) {
                AnnotationRegistry annotationRegistry = (AnnotationRegistry) entry.getValue();
                annotationRegistries.put(entry.getKey(), annotationRegistry);
            }
        }
        this.annotationRegistryHashMap = annotationRegistries;
    }

    /**
     * 6.将扫描到的类传入给AnnotationRegistry的handler方法中，具体处理由该接口实现类处理
     */
    private void invokeAnnotationHandler() {
        HashMap<String, AnnotationRegistry> annotationRegistryHashMap = this.annotationRegistryHashMap;
        Set<Map.Entry<String, AnnotationRegistry>> entries = annotationRegistryHashMap.entrySet();
        for (Class scannedClazz : containerFactory.getScannedClazz()) {
            for (Map.Entry<String, AnnotationRegistry> entry : entries) {
                Class<? extends Annotation> annotationClazz = containerFactory.getAnnotationRegistryCache().get(entry.getKey());
                AnnotationRegistry annotationRegistry = entry.getValue();
                if (scannedClazz.getAnnotation(annotationClazz) != null) {
                    annotationRegistry.handler(scannedClazz, containerFactory);
                }
            }
        }
    }

    /**
     * 初始化IOC容器
     */
    public void initialize(String packagePath) {

    }


}
