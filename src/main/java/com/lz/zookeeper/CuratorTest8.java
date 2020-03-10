package com.lz.zookeeper;



import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.utils.ZKPaths.PathAndNode;
import org.apache.zookeeper.ZooKeeper;

/**  
 *  开源客户端Curator之工具类
 *
 *  @author	Leowy Zhang
 */
public class CuratorTest8 {
	static String path_zkpaths =  "/tools/zkpaths";
	static CuratorFramework client_zkpaths = CuratorFrameworkFactory.builder()
			.connectString("192.168.6.233:2181")
			.sessionTimeoutMs(20000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	static String path_server =  "/tools/server";

	public static void main(String[] args) throws Exception {
		
		//ZKPaths 提供一些简单的API构建ZNode路径、递归创建和删除节点等
		client_zkpaths.start();
		ZooKeeper zookeeper = client_zkpaths.getZookeeperClient().getZooKeeper();
		System.out.println(ZKPaths.fixForNamespace(path_zkpaths, "/sub"));
		System.out.println(ZKPaths.makePath(path_zkpaths, "sub"));
		System.out.println(ZKPaths.getNodeFromPath(path_zkpaths+"/sub1"));
		PathAndNode pn = ZKPaths.getPathAndNode(path_zkpaths+"/sub1");
		System.out.println(pn.getNode());
		System.out.println(pn.getPath());
		
		String dir1 = path_zkpaths + "/child1";
		String dir2 = path_zkpaths + "/child2";
		
		ZKPaths.mkdirs(zookeeper, dir1);
		ZKPaths.mkdirs(zookeeper, dir2);
		System.out.println(ZKPaths.getSortedChildren(zookeeper, path_zkpaths));
		ZKPaths.deleteChildren(zookeeper, path_zkpaths, true);
		
		//TestingServer以便于zookeeper测试与开发，Curator提供了一种启动简易ZooKeeper服务的方法，
		//以便进行单元测试；需要引入依赖
		TestingServer server = new TestingServer(2181);
		CuratorFramework client_server = CuratorFrameworkFactory.builder()
				.connectString(server.getConnectString())
				.sessionTimeoutMs(5000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client_server.start();
		client_server.create()
			.creatingParentsIfNeeded()
			.forPath(path_server+"/test");
		System.out.println(client_server.getChildren().forPath(path_server));
        server.close();
		
		//TestCluster Curator提供了启动ZooKeeper集群的工具类
		TestingCluster cluster = new TestingCluster(3);
		cluster.start();
		Thread.sleep(2000);
		
		TestingZooKeeperServer leader = null;
		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.println(zs.getInstanceSpec().getServerId() + "-");
			System.out.println(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
			if (zs.getQuorumPeer().getServerState().equals("leading")) {
				leader = zs;
			}
		}
		leader.kill();
		System.out.println("-- After leader kill:");
		for (TestingZooKeeperServer zs : cluster.getServers()) {
			System.out.println(zs.getInstanceSpec().getServerId() + "-");
			System.out.println(zs.getQuorumPeer().getServerState() + "-");
			System.out.println(zs.getInstanceSpec().getDataDirectory().getAbsolutePath());
		}
		cluster.close();
	}

}