package com.ly.socket.tcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @Auther: Leowy Zhuang
 * @Date: 2020/8/31 09:16
 * @Description:
 */
public class TcpServer {
    public void getServer(int port) {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket server = serverSocket.accept();
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                while (true) {
                    System.out.println(in.readUTF());
                    Scanner scanner = new Scanner(System.in);
                    String content = scanner.nextLine();
                    System.out.println(content);
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    out.writeUTF(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = Integer.parseInt("9999");
        TcpServer tcp = new TcpServer();
        tcp.getServer(port);
        new Thread(() -> tcp.getServer(port)).start();
    }
}
