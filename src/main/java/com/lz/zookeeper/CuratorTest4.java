package com.lz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**  
 *  开源客户端Curator之Master选举
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest4 {
	static String path =  "/leowy6";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		client.start();
		
		LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
			@Override
			public void takeLeadership(CuratorFramework client) throws Exception {
				System.out.println("成为Master角色");
				Thread.sleep(3000);
				System.out.println("完成Master操作，释放Master权利");
			}
		});
		selector.autoRequeue();
		selector.start();
		Thread.sleep(Integer.MAX_VALUE);

	}

}