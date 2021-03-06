package com.uirise.webapp;

import com.uirise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        Resume r = new Resume("uuid1", "fhfgdjsg");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Method[] methods = r.getClass().getMethods();
        for (Method method:methods) {
            System.out.println(method);
        }
        Method method = r.getClass().getMethod("toString");
        System.out.println(method.invoke(r));

        System.out.println(r);
    }
}