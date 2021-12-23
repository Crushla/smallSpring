package org.smallSpring.inject;

import lombok.extern.slf4j.Slf4j;
import org.smallSpring.core.BeanContainer;
import org.smallSpring.utils.ClassUtil;
import org.smallSpring.utils.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {
    private BeanContainer beanContainer;
    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc(){
        if(ValidationUtil.isEmpty(beanContainer.getClasses())){
            log.warn("empty classSet in BeanContainer");
            return;
        }
        for (Class<?>clazz: beanContainer.getClasses()) {
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field: fields) {
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    Class<?> fieldClass = field.getType();
                    Object fieldValue = getFieldInstance(fieldClass,autowiredValue);
                    if (fieldValue == null){
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is:"+fieldClass.getName());
                    }else{
                        Object bean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,bean,fieldValue,true);
                    }
                }
            }
        }
    }

    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue!=null){
            return fieldValue;
        }else {
            Class<?>implementedClass = getImplementClass(fieldClass,autowiredValue);
            if (implementedClass!=null){
                return beanContainer.getBean(implementedClass);
            }else{
                return null;
            }
        }
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)){
            if (ValidationUtil.isEmpty(autowiredValue)){
                if (classSet.size()==1){
                    return classSet.iterator().next();
                }else{
                    throw new RuntimeException("multiple implemented classes for "+fieldClass.getName()+"please set @Autowired's value to pick one ");
                }
            }else{
                for (Class<?>clazz: classSet) {
                    if (autowiredValue.equals(clazz.getSimpleName())){
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
