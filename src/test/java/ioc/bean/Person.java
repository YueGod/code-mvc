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
public class Person {

    @Injection
    private User user;

    public void sayHi(){
        System.out.println("hi!");
    }

}
