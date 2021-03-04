package com.lz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class CreateZnode implements Watcher {

	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Received watched event:"+ event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			CreateZnode createZnode = new CreateZnode();
			ZooKeeper zoo = new ZooKeeper("192.168.6.233:2181", 10000, createZnode);
			createZnode.connectedSemaphore.await();
			System.out.println("zookeeper session established");
		
			String path1 = zoo.create("/leowy1", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("zookeeper create znode:"+ path1);
			
			//异步增加Znode
			zoo.create("/leowy2", "test".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
					new IStringCallback(), "I am context.");
			Thread.sleep(Integer.MAX_VALUE);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	

}

class IStringCallback implements StringCallback, AsyncCallback {
	
	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("Create Znode result: [" + rc + ","+ path + ","+ ctx + "," + name + "]");
	}
	
}
