package com.flamexander.netty.servers.blockserver;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8189);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            out.write(new byte[]{17, 21, 32});
            String x = in.nextLine();
            System.out.println("A: " + x);
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
