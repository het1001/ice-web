package com.het.ice.service.template;

public interface PageCallback {

	public void check();

	public void before();

	public void excute(int start, int limit);

	public void getTotal();

	public void after();
}
