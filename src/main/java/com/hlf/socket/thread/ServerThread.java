package com.hlf.socket.thread;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket = null;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;

        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            String data = "hello world";
            printWriter.println(data);
            printWriter.flush();


        } catch (Exception e) {

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
                e.printStackTrace();
            }

        }
    }
}
