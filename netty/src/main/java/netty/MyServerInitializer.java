package netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.ipfilter.RuleBasedIpFilter;

import java.util.List;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 13:04
 * @Description:
 */
public class MyServerInitializer extends ChannelInitializer<NioSocketChannel> {

    private List<String> addresses;

    public MyServerInitializer(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new RuleBasedIpFilter(new IPFilter(addresses)));
//        pipeline.addLast(new RuleBasedIpFilter(new IPFilter2()));
//        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
//        pipeline.addLast(new LengthFieldPrepender(4));
//        //字符串解码
//        pipeline.addLast(new ProtobufDecoder(ProtoObject));
//        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//        //字符串编码
//        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //自己定义的处理器
        pipeline.addLast(new MyServerHandler());
    }
}
