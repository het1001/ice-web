package com.het.ice.service.template;

public abstract class PageResultCallback<T> implements PageCallback {

	@Override
	public void check() {
	}

	@Override
	public void before() {
	}

	@Override
	public void excute(int start, int limit) {
	}

	@Override
	public void getTotal() {

	}

	@Override
	public void after() {
	}

	public T returnValue;

	public int total;
}
