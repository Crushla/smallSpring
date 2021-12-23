package org.smallSpring.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.smallSpring.aop.aspect.AspectInfo;
import org.smallSpring.utils.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AspectListExecutor implements MethodInterceptor {
    //被代理的类
    private Class<?> targetClass;
    @Getter
    private List<AspectInfo>sortAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        this.sortAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, new Comparator<AspectInfo>() {
            @Override
            public int compare(AspectInfo aspectInfo, AspectInfo t1) {
                return aspectInfo.getOrderIndex()-t1.getOrderIndex();
            }
        });
        return aspectInfoList;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;
        if (ValidationUtil.isEmpty(sortAspectInfoList)){
            return returnValue;
        }
        invokeBeforeAdvices(method,args);
        try {
            returnValue = methodProxy.invokeSuper(proxy, args);
            returnValue = invokeAfterReturningAdvices(method, args, returnValue);
        }catch (Exception e){
            invokeAfterThrowingAdvices(method, args,e);
        }
        return returnValue;
    }

    private void invokeBeforeAdvices(Method method, Object[] args) {
        for (AspectInfo aspectInfo:sortAspectInfoList){
            aspectInfo.getAspectObject().before(targetClass,method,args);
        }
    }

    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) {
        Object result = null;
        for (int i = sortAspectInfoList.size() - 1; i >= 0; i--) {
            result = sortAspectInfoList.get(i).getAspectObject().afterReturning(targetClass,method,args,returnValue);
        }
        return result;
    }

    private void invokeAfterThrowingAdvices(Method method, Object[] args, Exception e) {
        for (int i = sortAspectInfoList.size() - 1; i >= 0; i--) {
            sortAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass,method,args,e);
        }
    }
}
