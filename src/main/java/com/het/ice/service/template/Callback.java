package com.het.ice.service.template;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface Callback {

	public void check();

	public void before();

	public void excute() throws IllegalAccessException, IntrospectionException, InvocationTargetException;

	public void after();
}
