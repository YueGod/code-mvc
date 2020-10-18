package io.github.yuegod.mvc.core.boot;

import io.github.yuegod.mvc.core.annotation.EnableCodeMvc;
import io.github.yuegod.mvc.core.ioc.ContainerHandler;
import io.github.yuegod.mvc.util.StringUtils;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description CodeMvc启动类
 **/
public class CodeMvcBoot {

    private static ContainerHandler containerHandler = new ContainerHandler();

    public static void run(Class boot){
        EnableCodeMvc enableCodeMvc = (EnableCodeMvc) boot.getAnnotation(EnableCodeMvc.class);
        String scanPackage;
        if (StringUtils.isNotEmpty(enableCodeMvc.scanPackage())){
            scanPackage = enableCodeMvc.scanPackage();
        }else {
            scanPackage = boot.getPackage().getName();
        }
        //初始化容器扫描器
        containerHandler.initialize(scanPackage);
    }


}
