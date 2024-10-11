package com.hlf.socket;

import com.hlf.socket.thread.ServerThread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        //接收到client的连接信息
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ServerThread serverThread = new ServerThread();
        while (true) {
            executorService.execute(serverThread);
            Thread.sleep(10);
        }


    }
}
