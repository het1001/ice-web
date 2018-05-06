package com.het.ice.util;

import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeUtil {

    private static Logger logger = Logger.getLogger(InvokeUtil.class);

    /**
     *
     * @param key
     * @param obj
     * @return
     */
    public static Object get(String key, Object obj) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(key, obj.getClass());
            Method method = descriptor.getReadMethod();
            return method.invoke(obj);
        } catch (Exception e) {
            logger.error("InvokeUtil.get 失败了， key=" + key, e);
            throw e;
        }
    }

    /**
     *
     * @param key
     * @param obj
     * @param value
     */
    public static void set(String key, Object obj, Object value) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(key, obj.getClass());
            Method method = descriptor.getWriteMethod();
            method.invoke(obj, new Object[]{value});
        } catch (Exception e) {
            logger.error("InvokeUtil.set 失败了， key=" + key, e);
            throw e;
        }
    }
}
