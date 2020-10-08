package io.github.yuegod.mvc.core.configuration;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description 对Registry类的描述
 **/
public class RegistryDescription {

    private String instanceName;

    private Class registryClazz;

    private int order;

    public RegistryDescription(String instanceName, Class registryClazz, int order) {
        this.instanceName = instanceName;
        this.registryClazz = registryClazz;
        this.order = order;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Class getRegistryClazz() {
        return registryClazz;
    }

    public void setRegistryClazz(Class registryClazz) {
        this.registryClazz = registryClazz;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
