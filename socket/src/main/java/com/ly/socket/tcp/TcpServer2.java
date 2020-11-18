package com.ly.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 09:16
 * @Description:
 */
public class TcpServer2 {
    public void getServer(int port) {
        while (true) {
            new Thread(()->{
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket server = serverSocket.accept();
                    System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                    System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                    InputStream is = server.getInputStream();
                    byte[] b = new byte[1024];
                    OutputStream os = server.getOutputStream();
                    while (true) {
                        is.read(b);
                        System.out.println(new String(b, Charset.defaultCharset()));
                        Scanner scanner = new Scanner(System.in);
                        String content = scanner.nextLine();
                        System.out.println(content);
                        os.write(content.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt("9999");
        TcpServer2 tcp = new TcpServer2();
        tcp.getServer(port);
        new Thread(() -> tcp.getServer(port)).start();
    }
}
