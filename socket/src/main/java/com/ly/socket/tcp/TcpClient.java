package com.ly.socket.tcp;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        int port = 9999;
        System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
        try (Socket client = new Socket(serverName, port);) {
//            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

//            ByteBuffer header = ByteBuffer.allocate(2048);
//            header.clear();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String content = scanner.nextLine();
//                header.put(Byte.valueOf(content));
//                header.flip();
//                outToServer.write(header.array());
//                outToServer.flush();

                out.writeUTF(content);
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
//                byte[] rcvbuf = new byte[2048];
//                final int read = inFromServer.read(rcvbuf);
                System.out.println("服务器响应： " + in.readUTF());
//                System.out.println("服务器响应： " + rcvbuf);
            }
//            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}