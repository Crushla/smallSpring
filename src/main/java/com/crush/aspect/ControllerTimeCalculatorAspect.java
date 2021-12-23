package com.crush.aspect;

import lombok.extern.slf4j.Slf4j;
import org.smallSpring.aop.annotation.Aspect;
import org.smallSpring.aop.annotation.Order;
import org.smallSpring.aop.aspect.DefaultAspect;
import org.smallSpring.core.annotaion.Controller;

import java.lang.reflect.Method;
@Slf4j
@Aspect(value = Controller.class)
@Order(0)
public class ControllerTimeCalculatorAspect extends DefaultAspect {
    private long timeStamp;
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) {
        log.info("开始计时，执行类是[{}],执行方法是[{}],参数是[{}]",
                targetClass.getName(),method.getName(),args);
        timeStamp = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) {
        long endTime = System.currentTimeMillis();
        long cost = endTime - timeStamp;
        log.info("开始计时，执行类是[{}],执行方法是[{}],参数是[{}],返回值是[{}]时间是[{}]ms",
                targetClass.getName(),method.getName(),args,returnValue,cost);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) {
        super.afterThrowing(targetClass, method, args, e);
    }
}
