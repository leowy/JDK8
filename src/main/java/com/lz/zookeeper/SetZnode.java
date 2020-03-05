package com.lz.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**  
 *
 *  @author	Leowy Zhang
 */
public class SetZnode implements Watcher {

	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zoo = null;
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Received watched event:" + event);
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() || null == event.getPath()) {
				connectedSemaphore.countDown();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			SetZnode setZnode = new SetZnode();
			zoo = new ZooKeeper("192.168.6.233:2181", 10000, setZnode);
			setZnode.connectedSemaphore.await();
			System.out.println("zookeeper session established");
		
			Stat stat = zoo.setData("/leowy4", "leowy".getBytes(), -1);
			System.out.println("zookeeper Znode set: " + stat);
			
			//异步设置 Znode
			zoo.setData("/leowy4", "leowy".getBytes(), -1, new IStatCallback(), "I am context.");
			
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

class IStatCallback implements StatCallback,AsyncCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		System.out.println("set data znode result:["+rc+","+path+","+ctx+","+stat+"]");
	}
	
}
