package com.ly.socket.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 13:25
 * @Description:
 */
public class UdpServer {

    public void getServer() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(8888);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
