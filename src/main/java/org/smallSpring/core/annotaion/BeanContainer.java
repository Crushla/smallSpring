package org.smallSpring.core.annotaion;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smallSpring.core.utils.ClassUtil;
import org.smallSpring.core.utils.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {
    //判断容器是否被加载过
    private boolean loaded =false;
    //存放bean
    private final Map<Class<?>,Object>beanMap = new ConcurrentHashMap<>();
    //存放注解
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION =
            Arrays.asList(Component.class,Controller.class,Repository.class,Service.class);
    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder(){
            instance = new BeanContainer();
        }
    }
    public boolean isLoaded(){
        return loaded;
    }

    public int size(){
        return beanMap.size();
    }
    //获取bean实例
    public synchronized void loadBeans(String packageName){
        if (isLoaded()){
            log.warn("BeanContainer has been loaded");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if(ValidationUtil.isEmpty(classSet)){
            log.warn("extract nothing from packName"+packageName);
            return;
        }
        for (Class<?>clazz: classSet) {
            for (Class<?extends Annotation>annotation:BEAN_ANNOTATION) {
                //如果类有注解
                if(clazz.isAnnotationPresent(annotation)){
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }
            }
        }
        loaded = true;
    }

    public Object addBean(Class<?>clazz,Object bean){
        return beanMap.put(clazz,bean);
    }

    public Object removeBean(Class<?>clazz){
        return beanMap.remove(clazz);
    }

    public Object getBean(Class<?>clazz){
        return beanMap.get(clazz);
    }

    public Set<Class<?>>getClasses(){
        return beanMap.keySet();
    }

    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    public Set<Class<?>>getClassesByAnnotation(Class<? extends Annotation>annotation){
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?>clazz: keySet) {
            if (clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }

    public Set<Class<?>>getClassesBySuper(Class<?>interfaceOrClass){
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?>clazz: keySet) {
            if (interfaceOrClass.isAssignableFrom(clazz)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }
}
