package netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/10/30 14:19
 * @Description:
 */
public class ClientMap {

    private static Map<String, Channel> map = new ConcurrentHashMap<>();

    public static Map<String, Channel> getChannels() {
        return map;
    }

    public static void addSocketChannel(String id , Channel socketChannel) {
        map.put(id, socketChannel);
    }

    public static Channel getChannel(String id) {
        return map.get(id);
    }

    public static void removeChannel(String id) {
        map.remove(id);
    }
}
