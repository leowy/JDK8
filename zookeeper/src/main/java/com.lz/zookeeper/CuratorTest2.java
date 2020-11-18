package com.lz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 *  开源客户端Curator之异步接口
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest2 {
	
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	
	static CountDownLatch semaphore = new CountDownLatch(2);
	static ExecutorService tp = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		
		client.start();
		System.out.println("Main Thread : " + Thread.currentThread().getName());
		try {
			client.create()
				.creatingParentsIfNeeded()
				.inBackground( new BackgroundCallback() {
					
					@Override
					public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
						System.out.println("event[code:"+ event.getResultCode() + ",type:" + event.getType() + "]");
						System.out.println("Thread of processResult :" + Thread.currentThread().getName());
						semaphore.countDown();
					}
				}, tp)
				.forPath("/leowy5/test5");
			
			client.create()
			.creatingParentsIfNeeded()
			.inBackground( new BackgroundCallback() {
				
				@Override
				public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
					System.out.println("event[code:"+ event.getResultCode() + ",type:" + event.getType() + "]");
					System.out.println("Thread of processResult :" + Thread.currentThread().getName());
					semaphore.countDown();
				}
			})
			.forPath("/leowy5/test5","init".getBytes());
			
			semaphore.await();
			tp.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
