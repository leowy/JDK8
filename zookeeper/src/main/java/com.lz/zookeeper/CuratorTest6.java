package com.lz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**  
 *  开源客户端Curator之分布式计数器
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest6 {
	static String path =  "/counts";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	
	public static void main(String[] args) throws Exception {

		client.start();
		DistributedAtomicInteger atomicInteger = 
				new DistributedAtomicInteger(client, path, new  RetryNTimes(3, 1000));
		
		AtomicValue<Integer> rc = atomicInteger.add(4);
		System.out.println("Result:" + rc.succeeded() +"->"+ rc.postValue());
	}

}