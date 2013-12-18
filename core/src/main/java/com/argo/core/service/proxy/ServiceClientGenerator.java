package com.argo.core.service.proxy;

import org.springframework.beans.factory.InitializingBean;

/**
 * 描述 ：
 *
 * @author yaming_deng
 * @date 2013-1-11
 */
public interface ServiceClientGenerator extends InitializingBean {
	
	/**
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	<T> T getService(Class<T> clazz);
	
	/**
	 * @param <T>
	 * @param clazz
	 * @param serviceName
	 * @return
	 */
	<T> T getService(Class<T> clazz, String serviceName);
}
