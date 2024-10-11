package com.hlf.socket.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ServerThread extends Thread {
    private Socket socket = null;
    private static ServerSocket serverSocket;
    private final ObjectMapper objectMapper = new ObjectMapper();

    static {
        try {
            serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerThread() {
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.info(line);
            }

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            String data = "hello world";
            printWriter.println(data);
            printWriter.flush();
            log.info("客户端IP:" + socket.getInetAddress());


        } catch (Exception e) {
            log.error("错误信息：{}", e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("错误信息：{}", e.getMessage());
            }

        }
    }
}
