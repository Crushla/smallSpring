package org.smallSpring;

import com.crush.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.Test;
import org.smallSpring.aop.AspectWeaver;
import org.smallSpring.core.BeanContainer;
import org.smallSpring.inject.DependencyInjector;

import java.lang.annotation.Target;

public class AspectWeaverTest {
    @Test
    public void doAopTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.crush");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController headLineOperationController = (HeadLineOperationController)beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null,null);
    }
}
