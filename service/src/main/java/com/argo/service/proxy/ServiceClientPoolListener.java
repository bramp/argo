package com.argo.service.proxy;

import com.argo.service.listener.ServicePoolListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: yamingdeng
 * Date: 13-12-15
 * Time: 下午1:12
 */
public class ServiceClientPoolListener implements ServicePoolListener, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * key is the name of service
     * value is the server addresses of those service.
     */
    private ConcurrentHashMap<String, List<String>> servicesMap = new ConcurrentHashMap<String, List<String>>();
    private static AtomicLong shift = new AtomicLong();

    @Override
    public void onServiceChanged(String name, List<String> urls) {
        this.servicesMap.put(name, urls);
    }

    @Override
    public String select(String serviceName){
        List<String> servers = this.servicesMap.get(serviceName);
        if(servers != null && servers.size()>0){
            Long index = shift.incrementAndGet();
            index = index % servers.size();
            return servers.get(index.intValue());
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public  static ServiceClientPoolListener instance = null;
}
