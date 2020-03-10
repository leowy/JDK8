package com.lz.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**  
 *  开源客户端Curator之分布式Barrier
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest7 {
	static String path =  "/barriers";
//	static DistributedBarrier barrier;
	
	public static void main(String[] args) throws Exception {

		
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				try {
					CuratorFramework client = CuratorFrameworkFactory.builder()
							.connectString("192.168.6.233:2181")
							.sessionTimeoutMs(20000)
							.retryPolicy(new ExponentialBackoffRetry(1000, 3))
							.build();
					client.start();
//					barrier = new DistributedBarrier(client, path);
					DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path, 5);
					Thread.sleep(Math.round(Math.random() * 3000));
					System.out.println(Thread.currentThread().getName() + "号barrier设置");
//					barrier.setBarrier();//设置barrier节点
//					barrier.waitOnBarrier();//阻塞直到barrier节点出现
					barrier.enter();
					Thread.sleep(Math.round(Math.random() * 3000));
					System.out.println(Thread.currentThread().getName() + "启动");
					barrier.leave();
					System.out.println(Thread.currentThread().getName() + "退出");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
//		Thread.sleep(20000);
//		barrier.removeBarrier();//删除barrier节点
		
	}

}