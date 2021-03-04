package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Author: Leowy Zhuang
 * @Date: 2021/01/15 09:28
 * @Description: TODO
 */
public class TestEncoder extends MessageToByteEncoder<Test> {//MessageToByteEncoder继承outbandler

    @Override
    protected void encode(ChannelHandlerContext ctx, Test msg, ByteBuf out) throws Exception {
        //工具类：将object转换为byte[]
        System.out.println("PersonEncoder--out");
        byte[] datas = objectToByte(msg);
        out.writeBytes(datas);
        ctx.flush();
    }


    /**
     * 使用IO的outputstream流将object转换为byte[]
     */
    public byte[] objectToByte(Object obj) {
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