package ioc;

import io.github.yuegod.mvc.core.annotation.EnableCodeMvc;
import io.github.yuegod.mvc.core.boot.CodeMvcBoot;
import io.github.yuegod.mvc.core.configuration.RegistryAnnotationConfiguration;
import io.github.yuegod.mvc.core.configuration.RegistryDescription;
import io.github.yuegod.mvc.core.ioc.AchieveContainerFactory;
import ioc.bean.Person;
import org.junit.Test;

import java.util.Set;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description
 **/
@EnableCodeMvc
public class IoCTest extends AchieveContainerFactory {

    @Test
    public void start(){
        CodeMvcBoot.run(IoCTest.class);
        Person person  = getInstance(Person.class.getName(),Person.class);
        System.out.println(person);
    }

}
