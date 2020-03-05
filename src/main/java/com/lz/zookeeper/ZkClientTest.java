package com.lz.zookeeper;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

/**  
 *  开源客户端ZkClient
 *
 *  @author	Zhang Wei
 */
public class ZkClientTest {
	
	public static void main(String[] args) {
		
		//创建会话
		ZkClient zkClient = new ZkClient("192.168.6.233:2181", 20000);
		System.out.println("zookeeper crate session");
		
		//监听对应znode以及子节点变化
		zkClient.subscribeChildChanges("/", new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println(parentPath+"'s child changed , currentChilds:" + currentChilds);
			}
		});

		//递归创建znode节点
		zkClient.createPersistent("/leowy1/test", true);
		System.out.println("zookeeper create znode: /leowy1/test");
		
		//递归删除znode节点
		zkClient.deleteRecursive("/leowy1");
		System.out.println("zookeeper delete znode: /leowy1");
		
		
		zkClient.createPersistent("/leowy2/test1", true);
		zkClient.createPersistent("/leowy2/test2", true);
		
		//获取指定节点的子节点
		List<String> znodes = zkClient.getChildren("/leowy2");
		System.out.println("zookeeper get children:"+znodes);
		
		//写数据
		zkClient.writeData("/leowy2/test1", "I am test1");
		zkClient.writeData("/leowy2/test2", "I am test2");
		
		String test1 = zkClient.readData("/leowy2/test1");
		String test2 = zkClient.readData("/leowy2/test2");
		System.out.println("test1:"+test1+",test2:"+test2);
		
		//检查节点是否存在
		boolean rootNode = zkClient.exists("/");
		boolean znode = zkClient.exists("/leowy2/test1");
		System.out.println("rootNone:" + rootNode + ",znode :" + znode);
		
		zkClient.deleteRecursive("/leowy2");
		
		boolean znode1 = zkClient.exists("/leowy2/test1");
		System.out.println("rootNone:" + rootNode + ",znode :" + znode1);
	}

}
