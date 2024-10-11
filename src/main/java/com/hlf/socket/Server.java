package com.hlf.socket;

import com.hlf.socket.thread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = null;

        while (true) {
            //接收到client的连接信息
            socket = serverSocket.accept();

            //线程处理信息
            Thread serverThread = new ServerThread(socket);
            serverThread.start();

            System.out.println("客户端IP:" + socket.getInetAddress());

        }

    }
}
