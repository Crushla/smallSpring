package org.smallSpring.core.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
@Slf4j
public class ClassUtil {
    private static final String FILE_PROTOCOL = "file";

    public static Set<Class<?>>extractPackageClass(String packageName){
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if(url == null){
            log.warn("unable to retrieve anything from package:"+packageName);
            return null;
        }
        Set<Class<?>>classSet = null;
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> classSet, File packageDirectory, String packageName) {
        if(!packageDirectory.isDirectory()){
            return;
        }
        //如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件和文件夹
        File[] files = packageDirectory.listFiles(new FileFilter() {
            //如果为true就加入到files中
            @Override
            public boolean accept(File file) {
                //如果是目录
                if (file.isDirectory()) {
                    return true;
                } else {
                    //获取绝对路径
                    String absolutePath = file.getAbsolutePath();
                    //如果是.class文件就直接加载
                    if (absolutePath.endsWith(".class")) {
                        addToClassSet(absolutePath);
                    }
                    return false;
                }
            }
            //将.class文件加载到Set下
            private void addToClassSet(String absolutePath){
                absolutePath= absolutePath.replace(File.separator, ".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));
                Class<?> clazz = loadClass(className);
                classSet.add(clazz);
            }
        });
        if (files!=null){
            for (File file: files) {
                extractClassFile(classSet,file, packageName);
            }
        }
    }

    public static Class<?>loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static <T> T newInstance(Class<?>clazz,boolean accessible){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T) constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error",e);
            throw new RuntimeException(e);
        }
    }

    public static void setField(Field field, Object target,Object value,boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target,value);
        } catch (IllegalAccessException e) {
            log.error("setField error",e);
            throw new RuntimeException(e);
        }
    }
}
