package netty;

//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 13:05
 * @Description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        ByteBuf byteBuf = (ByteBuf) msg;
        final int anInt = byteBuf.getInt(0);
        System.out.println(Integer.toHexString(anInt));
        final int anInt1 = byteBuf.getInt(4);
        System.out.println(Integer.toHexString(anInt1));
        final int anInt2 = byteBuf.getInt(8);
        System.out.println(Integer.toHexString(anInt2));
        final int anInt3 = byteBuf.getInt(12);
        System.out.println(Integer.toHexString(anInt3));
//        final ByteBuf bytes = byteBuf.getBytes(16, byteBuf);
        System.out.println(ByteBufUtil.hexDump(byteBuf));
        ByteBuf byteBuf1 = byteBuf.slice(0, 4);
        System.out.println(ByteBufUtil.hexDump(byteBuf1));
//        System.out.println(ByteBufUtil.hexDump(byteBuf).substring(0,32 + anInt3 * 2));
        System.out.println(ByteBufUtil.hexDump(byteBuf));
        ByteBuf byteBuf2 = byteBuf.slice(0, 4);
        System.out.println(ByteBufUtil.hexDump(byteBuf2));
//        System.out.println(bytes.toString(StandardCharsets.UTF_8));
        System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));

        // 回复数据到客户端
        System.out.println(new Date() + ": 服务端写出数据");
        ByteBuf out = getByteBuf(ctx,"写入成功");
        ctx.channel().writeAndFlush(out);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress  socketAddress = (InetSocketAddress )ctx.channel().remoteAddress();
        final String hostAddress = socketAddress.getAddress().getHostAddress();
        final int port = socketAddress.getPort();

        final String id = ctx.channel().id().asLongText();
        System.out.println("id:" + id);
        ClientMap.addSocketChannel(id, (NioSocketChannel) ctx.channel());

        System.out.println("启动完成");
        System.out.println(hostAddress+":" + port);

        ctx.channel().writeAndFlush(getByteBuf(ctx,"连接成功"));

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        InetSocketAddress address = (InetSocketAddress)ctx.channel().remoteAddress();
        System.out.println("断开连接[" + address.getHostString() + ":" + address.getPort() + "]");
        ClientMap.removeChannel(ctx.channel().id().asLongText());
        ctx.close();
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String next) {
//        Scanner scanner = new Scanner(System.in);
//        final String next = scanner.next();

        byte[] bytes = (next).getBytes(StandardCharsets.UTF_8);
//        byte[] bytes = (new Date() + "Server!").getBytes(StandardCharsets.UTF_8);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.fireChannelReadComplete();
        System.out.println("read complete is running ....");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("event trigger is running ....");
    }

}
