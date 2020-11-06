package io.github.yuegod.mvc.core.common;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 容器工程后置处理器
 **/
public interface ContainerFactoryPostProcessor {

    /**
     * 在容器对类做实例化处理之前的一些方法
     * @param clazz 被扫描到的类
     * @param instanceName 实例称
     * @return 返回一个类对象
     */
     default Class<?> containerFactoryBeforePostProcessor(String instanceName,Class clazz){
      return clazz;
     }

     /**
      * 在容器对类实例化后做的一些处理
      * @param instance 实例化后的实例对象，未进行属性赋值
      * @param instanceName 实例名称
      * @return 返回一个处理好的对象
      */
     default Object containerFactoryAfterPostProcessor(String instanceName,Object instance){
         return instance;
     }

}
