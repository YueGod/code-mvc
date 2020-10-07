package io.github.yuegod.mvc.core.common;

/**
 * @author quziwei
 * @date 2020/10/07
 * @description 在创建实例的时候，会判断类有没有实现该接口，如果实现该接口则会调用getObject方法获取实例并将该实例放入单例缓存池当中
 **/
public interface Factory<T> {

    /**
     * 通过该方法可以自行选择创建哪个实例
     *
     * @return 返回一个Object
     */
    T getObject();

}
