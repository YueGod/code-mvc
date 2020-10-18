package ioc.bean;

import io.github.yuegod.mvc.core.annotation.IoC;
import lombok.Data;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description
 **/
@IoC
@Data
public class User {

    private String name = "张三";

}
