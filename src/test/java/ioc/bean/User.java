package ioc.bean;

import io.github.yuegod.mvc.core.annotation.Injection;
import io.github.yuegod.mvc.core.annotation.IoC;
import lombok.Data;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description
 **/
@IoC
public class User {

    private String name = "张三";

    @Injection
    private Person person;

}
