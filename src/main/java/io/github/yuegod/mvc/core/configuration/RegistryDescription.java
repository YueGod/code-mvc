package io.github.yuegod.mvc.core.configuration;

import java.util.Objects;

/**
 * @author quziwei
 * @date 2020/10/08
 * @description 对Registry类的描述
 **/
public class RegistryDescription {

    private String instanceName;

    private Class<?> registryClazz;

    private int order;

    public RegistryDescription(String instanceName, Class<?> registryClazz, int order) {
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

    public Class<?> getRegistryClazz() {
        return registryClazz;
    }

    public void setRegistryClazz(Class<?> registryClazz) {
        this.registryClazz = registryClazz;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistryDescription that = (RegistryDescription) o;
        return order == that.order &&
                Objects.equals(instanceName, that.instanceName) &&
                Objects.equals(registryClazz, that.registryClazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceName, registryClazz, order);
    }
}
