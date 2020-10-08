package ioc;

import io.github.yuegod.mvc.core.configuration.RegistryAnnotationConfiguration;
import io.github.yuegod.mvc.core.configuration.RegistryDescription;
import org.junit.Test;

import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description
 **/
public class IoCTest {

    @Test
    public void sorted() {
        Set<Class> classes = ContainerTest.getClasses("ioc");
        System.out.println(classes.size());
        RegistryAnnotationConfiguration configuration = new RegistryAnnotationConfiguration();
        Set<RegistryDescription> registryClazz = configuration.getRegistryClazz(classes);
        registryClazz.forEach(registryDescription -> {
            System.out.println(registryDescription.getInstanceName());
        });
    }

}
