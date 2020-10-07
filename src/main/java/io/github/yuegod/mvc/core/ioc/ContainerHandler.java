package io.github.yuegod.mvc.core.ioc;

import io.github.yuegod.mvc.core.annotation.Registry;
import io.github.yuegod.mvc.core.common.AnnotationRegistry;
import io.github.yuegod.mvc.core.configuration.RegistryAnnotationConfiguration;
import io.github.yuegod.mvc.util.StringUtils;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 容器处理
 **/
public class ContainerHandler extends AbstractContainer {

    private RegistryAnnotationConfiguration registryAnnotationConfiguration;

    public static Set<Class> annotationRegistries = new HashSet<>(16);

    /**
     * 1.先扫描出所有的类并添加到对应的缓存容器中
     */
    private void scan(String packagePath) {
        ContainerScanner scanner = new ContainerScanner();
        if (StringUtils.isNotEmpty(packagePath)){
            Set<Class<?>> classes = scanner.scan(packagePath);
            scannedClazz.addAll(classes);
        }else {
            Set<Class<?>> classes = scanner.scan();
            scannedClazz.addAll(classes);
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
    private void registryConfiguration(){
        Set<Class> registryClazz = registryAnnotationConfiguration.getRegistryClazz(scannedClazz);

    }

    /**
     * 初始化IOC容器
     */
    public void initialize(String packagePath){

    }

    @Override
    protected Object createInstance(Class clazz) {
        return null;
    }
}
