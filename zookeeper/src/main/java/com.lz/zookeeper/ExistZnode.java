package com.lz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class ExistZnode implements Watcher {

	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zoo = null;
	
	@Override
	public void process(WatchedEvent event) {
		try {
			System.out.println("Received watched event:" + event);
			if (KeeperState.SyncConnected == event.getState()) {
				if (EventType.None == event.getType() || null == event.getPath()) {
					connectedSemaphore.countDown();
				} else if (EventType.NodeCreated == event.getType()) {
					System.out.println("Node(" + event.getPath() + ") created");
					zoo.exists(event.getPath(), true);
				} else if (EventType.NodeDataChanged == event.getType()) {
					System.out.println("Node(" + event.getPath() + ") datachanged");
					zoo.exists(event.getPath(), true);
				} else if (EventType.NodeDeleted == event.getType()) {
					System.out.println("Node(" + event.getPath() + ") deleted");
					zoo.exists(event.getPath(), true);
				}
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			ExistZnode existZnode = new ExistZnode();
			zoo = new ZooKeeper("192.168.6.233:2181", 10000, existZnode);
			existZnode.connectedSemaphore.await();
			System.out.println("zookeeper session established");
		
			Stat stat = zoo.exists("/leowy5", true);
			System.out.println("zookeeper Znode exist: " + stat);

			zoo.create("/leowy5", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zoo.setData("/leowy5", "test".getBytes(), -1);
			zoo.exists("/leowy5", true, new IStatCallback(), "I am context");
			zoo.delete("/leowy5", -1);
			
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
