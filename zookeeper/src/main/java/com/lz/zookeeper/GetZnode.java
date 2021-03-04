package com.lz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.AsyncCallback.Children2Callback;
import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class GetZnode implements Watcher {

	private CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zoo = null;
	
	@Override
	public void process(WatchedEvent event) {
		System.out.println("Received watched event:"+ event);
		if (KeeperState.SyncConnected == event.getState()) {
			if (EventType.None == event.getType() || null == event.getPath()) {
				connectedSemaphore.countDown();
			} else if (EventType.NodeDataChanged == event.getType()) {
				zoo.getData(event.getPath(), true, new IDataCallback(), "I am context.");
			} else if (EventType.NodeChildrenChanged == event.getType()) {
				try {
					System.out.println("reget child :"+ zoo.getChildren(event.getPath(), true));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			GetZnode getZnode = new GetZnode();
			zoo = new ZooKeeper("192.168.6.233:2181", 10000, getZnode);
			getZnode.connectedSemaphore.await();
			System.out.println("zookeeper session established");

			List<String> znodes = zoo.getChildren("/", true);
			System.out.println("zookeeper Znode get: " + znodes);
			zoo.create("/leowy3", "test2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			
			//异步获取 Znode
			zoo.getChildren("/", true, new IChildren2Callback(), "I am context.");
			zoo.create("/leowy4", "test2".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			
			
			zoo.getData("/", true, new IDataCallback(), "I am context.");
			zoo.setData("/leowy4", "1".getBytes(), 0);
			
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

class IChildren2Callback implements Children2Callback, AsyncCallback{

	@Override
	public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
		System.out.println("get children znode result :["+rc+","+path+","+ctx+","+children+","+stat+"]");
	}
	
}

class IDataCallback implements DataCallback, AsyncCallback{

	@Override
	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		System.out.println("get data znode result:["+rc+","+path+","+ctx+","+data+","+stat+"]");
	}
	
	
}