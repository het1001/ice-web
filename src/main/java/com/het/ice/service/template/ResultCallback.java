package com.het.ice.service.template;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public abstract class ResultCallback<T> implements Callback {

	@Override
	public void check() {
	}

	@Override
	public void before() {
	}

	@Override
	public void excute() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
	}

	@Override
	public void after() {
	}

	public T returnValue;
}
