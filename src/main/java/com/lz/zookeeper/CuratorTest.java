package com.lz.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**  
 *  开源客户端Curator
 *
 *  @author	Zhang Wei
 */
public class CuratorTest {
	
	public static void main(String[] args) {
		
		//创建会话
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);//重试策略
		CuratorFramework client = CuratorFrameworkFactory.newClient(
				"192.168.6.233:2181", 30000, 20000, retryPolicy);
		client.start();
		System.out.println("zookeeper create session");
		
		try {
			
			//创建节点
			client.create()
//			      .withMode(CreateMode.EPHEMERAL)
				  .creatingParentsIfNeeded()
				  .forPath("/leowy4/test1", "I am test1".getBytes());
			System.out.println("zookeeper create znode : /leowy4/test1");
			
			//获取节点data 以及 stat
			Stat stat = new Stat();
			byte[] data =client.getData()
			                   .storingStatIn(stat)
			                   .forPath("/leowy4/test1");
			System.out.println("zookeeper get data :" + data + ",stat :" + stat);
			
			//更新节点data
			Stat stat2 = client.setData()
			                   .withVersion(stat.getVersion())
			                   .forPath("/leowy4/test1","I am update data".getBytes());
			System.out.println("zookeeper set data : success , stat :" + stat2);
			
			//删除节点
			client.delete()
			      .guaranteed()  //强制
			      .deletingChildrenIfNeeded() //递归
//			      .withVersion(-1) //版本
			      .forPath("/leowy4");
			System.out.println("zookeeper delete znode: /leowy4");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
