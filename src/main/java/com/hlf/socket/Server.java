package com.hlf.socket;

import com.hlf.socket.thread.ServerThread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        //接收到client的连接信息
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        ServerThread serverThread = new ServerThread();
        while (true) {
            executorService.execute(serverThread);
            Thread.sleep(10);
        }
    }
}
