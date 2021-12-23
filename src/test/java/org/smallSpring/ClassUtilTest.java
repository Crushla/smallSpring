package org.smallSpring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.smallSpring.utils.ClassUtil;

import java.util.Set;

public class ClassUtilTest {
    @Test
    public void extractPackageClassTest() throws Exception {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("org.smallSpring.core.annotaion");
        System.out.println(classSet);
        assert classSet != null;
        Assertions.assertEquals(classSet.size(),4);
    }
}
