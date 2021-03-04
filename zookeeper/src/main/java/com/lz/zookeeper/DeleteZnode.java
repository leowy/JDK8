package com.lz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class DeleteZnode implements Watcher {

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
			DeleteZnode deleteZnode = new DeleteZnode();
			ZooKeeper zoo = new ZooKeeper("192.168.6.233:2181", 10000, deleteZnode);
			deleteZnode.connectedSemaphore.await();
			System.out.println("zookeeper session established");
		
			zoo.delete("/leowy1", 0);
			System.out.println("zookeeper Znode delete: /leowy1");
			
			//异步删除Znode
			zoo.delete("/leowy2", 0, new IVoidCallback(), "I am context.");
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

class IVoidCallback implements VoidCallback, AsyncCallback {
	
	@Override
	public void processResult(int rc, String path, Object ctx) {
		System.out.println("Delete Znode result: [" + rc + ","+ path + ","+ ctx +  "]");
		
	}
	
}
