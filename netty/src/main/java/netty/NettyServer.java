package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 13:03
 * @Description:
 */
public class NettyServer {
//    public static void main(String[] args) throws Exception {
//
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup wokerGroup = new NioEventLoopGroup();
//
//        try{
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            //在服务器端的handler()方法表示对bossGroup起作用，而childHandler表示对wokerGroup起作用
//            serverBootstrap.group(bossGroup,wokerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new MyServerInitializer());
//
//            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
//            channelFuture.channel().closeFuture().sync();
//        }finally {
//            bossGroup.shutdownGracefully();
//            wokerGroup.shutdownGracefully();
//        }
//    }
private static final int PORT = 12345;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(()-> {

            List<String> addresses = new ArrayList<>();
            addresses.add("127.0.0.1");

            NioEventLoopGroup boosGroup = new NioEventLoopGroup();
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();

            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new MyServerInitializer(addresses));
            bind(serverBootstrap, PORT);
        });
        t.start();
        new Thread(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("=============>");
                ClientMap.getChannels().forEach((s, channel) -> {
                    ByteBuf byteBuf = channel.alloc().buffer();
//                    byteBuf.writeBytes("testing.....".getBytes());
                    Hobby hobby = new Hobby();
                    hobby.setTest("ok");
                    Test test = new Test();
                    test.setName("zhangw");
                    test.setAge(18);
                    test.setHobby(hobby);


//                    byteBuf.writeBytes(test);
                    channel.writeAndFlush(test);

                });
            }
        }).start();
        t.join();
        System.out.println("======>end");
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }

    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
