package org.smallSpring.aop.aspect;

import java.lang.reflect.Method;

public abstract class DefaultAspect {
    public void before(Class<?>targetClass, Method method, Object[]args){

    }
    public Object afterReturning(Class<?>targetClass,Method method,Object[]args,Object returnValue){
        return returnValue;
    }
    public void afterThrowing(Class<?>targetClass,Method method,Object[]args,Throwable e){

    }
}
