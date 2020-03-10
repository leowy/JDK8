package com.lz.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**  
 *  开源客户端Curator之分布式锁
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest5 {
	static String path =  "/locks";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	
	public static void main(String[] args) throws Exception {

		client.start();
		InterProcessMutex lock = new InterProcessMutex(client, path);
		final CountDownLatch down = new CountDownLatch(1);
		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						down.await();
						lock.acquire();
					} catch (Exception e) {
						e.printStackTrace();
					}
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
					System.out.println("order No. "+ sdf.format(new Date()));
					
					try {
						lock.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			
		}
		
		down.countDown();
	}

}