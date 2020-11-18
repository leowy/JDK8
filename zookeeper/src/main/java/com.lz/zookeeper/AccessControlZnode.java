package com.lz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class AccessControlZnode implements Watcher {
	
	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Received watched event:"+ event);
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() || null == event.getPath()) {
				connectedSemaphore.countDown();
			} 
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			AccessControlZnode aclZnode = new AccessControlZnode();
			ZooKeeper zoo = new ZooKeeper("192.168.6.233:2181", 20000, aclZnode);
			System.out.println("zookeeper session established");
			zoo.addAuthInfo("digest", "foo:true".getBytes());
			aclZnode.connectedSemaphore.await();
			
			String path1 = zoo.create("/leowy6", "test".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			System.out.println("zookeeper create znode:"+ path1);
			
			AccessControlZnode aclZnode1 = new AccessControlZnode();
			ZooKeeper zoo2 = new ZooKeeper("192.168.6.233:2181", 20000, aclZnode1);
			zoo2.addAuthInfo("digest", "foo:false".getBytes());
			System.out.println(zoo2.getData("/leowy6", true, null));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}

}
