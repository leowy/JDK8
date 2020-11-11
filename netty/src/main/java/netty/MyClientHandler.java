package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 13:05
 * @Description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印出客户端地址
        System.out.println(ctx.channel().remoteAddress() + ", " + msg);
        ctx.channel().writeAndFlush("form server: " + UUID.randomUUID());
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
//        ctx.channel().writeAndFlush("ok");
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("启动完成");
        ctx.channel().writeAndFlush("ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
