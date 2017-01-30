package com.het.ice.service.template;

public abstract class ResultCallback<T> implements Callback {

	@Override
	public void check() {
	}

	@Override
	public void before() {
	}

	@Override
	public void excute() {
	}

	@Override
	public void after() {
	}

	public T returnValue;
}
