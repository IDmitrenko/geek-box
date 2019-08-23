package com.geerkbrains.netty.example.client;

import java.io.IOException;
import java.net.Socket;

import com.geerkbrains.netty.example.common.AbstractMessage;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

public class Network {
    private static Socket socket;
    private static ObjectEncoderOutputStream out;  // отправка
    private static ObjectDecoderInputStream in;    // получение объекта (для этого нужен channel)

    public static void start() {
        try {
            socket = new Socket("localhost", 8189);
            out = new ObjectEncoderOutputStream(socket.getOutputStream());
            in = new ObjectDecoderInputStream(socket.getInputStream(), 50 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendMsg(AbstractMessage msg) {
        // отправка сообщения (объекта) о том, что мы хотим получить файл
        try {
            out.writeObject(msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static AbstractMessage readObject() throws ClassNotFoundException, IOException {
        // считывание объекта
        Object obj = in.readObject();
        return (AbstractMessage) obj;
    }
}
