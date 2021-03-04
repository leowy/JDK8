package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @Author: Leowy Zhuang
 * @Date: 2021/01/15 09:27
 * @Description: TODO
 */
public class TestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //工具类：将ByteBuf转换为byte[]
        ByteBufToBytes read = new ByteBufToBytes();
        byte[] bytes = read.read(in);
        //工具类：将byte[]转换为object
        Object obj = byteToObject(bytes);
        out.add(obj);
    }


    /**
     * 使用IO的inputstream流将byte[]转换为object
     *
     * @param bytes
     * @return
     */
    public Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    class ByteBufToBytes {
        /**
         * 将ByteBuf转换为byte[]
         *
         * @param datas
         * @return
         */
        public byte[] read(ByteBuf datas) {
            byte[] bytes = new byte[datas.readableBytes()];// 创建byte[]
            datas.readBytes(bytes);// 将ByteBuf转换为byte[]
            return bytes;
        }
    }

}
