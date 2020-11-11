package com.ly.socket.tcp;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class TcpClient2 {
    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        int port = 9999;
        System.out.println("连接到主机：" + serverName + " ，端口号：" + port);

        new Thread(() -> {
            try (Socket client = new Socket(serverName, port);) {
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

                new Thread(() -> {

                    byte[] b = new byte[1024];
                    int length;

                    try {
                        InputStream is = client.getInputStream();
                        while ((length = is.read(b)) != -1) {
                            System.out.println("receiver: " + new String(b, 0, length));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();

                while (true) {
                    try {
                        // 每两秒发一个带时间的 hello world
                        Scanner scanner = new Scanner(System.in);
                        String cont = scanner.next();

                        int len = cont.getBytes().length;
                        System.out.println(cont);
                        System.out.println(len);

                        int head = 0x1A2B3C4D;
                        int type = 0XFFEEFFEE;
                        int seq = 1;
                        int length = 0;

                        int msgLen = 16 + len;
                        ByteBuffer buf = ByteBuffer.allocate(msgLen);

                        buf.putInt(head);
                        buf.putInt(type);
                        buf.putInt(seq);
                        buf.putInt(len);
                        buf.put(cont.getBytes("UTF-8"));
                        client.getOutputStream().write(buf.array());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}