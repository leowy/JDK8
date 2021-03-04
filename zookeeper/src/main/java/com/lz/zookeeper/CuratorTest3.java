package com.lz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**  
 *  开源客户端Curator之事件监听
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest3 {
	
	static String path =  "/leowy6";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	
	public static void main(String[] args) {
		
		client.start();
		try {
//			client.create()
//			      .creatingParentsIfNeeded()
//			      .withMode(CreateMode.EPHEMERAL)
//			      .forPath("/leowy6");
//			final NodeCache cache = new NodeCache(client, path, false);
//			cache.start();
//			cache.getListenable()
//			     .addListener(new NodeCacheListener() {
//					
//					@Override
//					public void nodeChanged() throws Exception {
//						System.out.println("Node data update,new data : " 
//					+ new String(cache.getCurrentData().getData()));
//					}
//				});
//			client.setData().forPath(path, "init".getBytes());
//			Thread.sleep(1000);
//			client.delete().deletingChildrenIfNeeded().forPath(path);
			
			final PathChildrenCache cache = new PathChildrenCache(client, path, true);
			cache.start(StartMode.POST_INITIALIZED_EVENT);
			cache.getListenable()
			     .addListener(new PathChildrenCacheListener() {
					
					@Override
					public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
						switch (event.getType()) {
						case CHILD_ADDED:
							System.out.println("CHILD_ADDED," + event.getData().getPath());
							break;
						case CHILD_UPDATED:
							System.out.println("CHILD_UPDATED," + event.getData().getPath());
							break;
						case CHILD_REMOVED:
							System.out.println("CHILD_REMOVED," + event.getData().getPath());
							break;

						default:
							break;
						}
					}
				});
			
			client.create()
			      .forPath(path);
			Thread.sleep(1000);
			client.create()
				  .forPath(path+"/test");
			Thread.sleep(1000);
			client.delete()
			      .forPath(path + "/test");
			Thread.sleep(1000);
			client.delete()
			      .forPath(path);

			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
