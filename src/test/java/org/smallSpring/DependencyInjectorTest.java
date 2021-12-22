package org.smallSpring;

import com.crush.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.smallSpring.core.annotaion.BeanContainer;
import org.smallSpring.inject.DependencyInjector;

public class DependencyInjectorTest {
    @Test
    public void doIocTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.crush");
        Assertions.assertEquals(true,beanContainer.isLoaded());
        MainPageController mainPageController =(MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);
        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
    }
}
