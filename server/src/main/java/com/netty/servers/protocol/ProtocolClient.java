package com.netty.servers.protocol;

import java.io.DataOutputStream;
import java.net.Socket;

public class ProtocolClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8188);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.write(16);
            String filename = "java.txt";
            byte[] bytes = filename.getBytes();
            out.writeInt(bytes.length);
            out.write(bytes);
            out.flush();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
