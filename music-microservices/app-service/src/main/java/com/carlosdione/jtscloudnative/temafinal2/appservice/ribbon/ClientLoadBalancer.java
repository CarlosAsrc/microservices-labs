package com.carlosdione.jtscloudnative.temafinal2.appservice.ribbon;

import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class ClientLoadBalancer {
	
	private List<Server> servers;
	
	public ClientLoadBalancer(List<Server> servers) {
		this.servers = servers;
	}
	
	public String getServer() {
		ILoadBalancer loadBalancer = new BaseLoadBalancer();
		loadBalancer.addServers(servers);
		return loadBalancer.chooseServer(null).toString();    
	}
}
