package com.hlf.socket;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);


        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {

            //获取输出流  向服务器发送信息
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println("hello world");
            printWriter.flush();
            socket.shutdownOutput();

            //接收服务端发送数据
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

        } finally {
            bufferedReader.close();
            inputStream.close();
            outputStream.close();
            socket.close();
            printWriter.close();
        }


    }
}
