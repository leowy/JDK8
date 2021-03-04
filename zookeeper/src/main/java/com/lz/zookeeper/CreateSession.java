package com.lz.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**  
 *
 *  @author	Leowy Zhang
 */
public class CreateSession implements Watcher {

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
			CreateSession createSession = new CreateSession();
			new ZooKeeper("192.168.6.233:2181", 10000, createSession);
			createSession.connectedSemaphore.await();
			System.out.println("zookeeper session established");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
