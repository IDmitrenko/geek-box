package com.netty.servers.serialization;

import java.io.IOException;
import java.net.Socket;

import com.netty.common.MyMessage;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

public class Client {
    public static void main(String[] args) {
        // перекидываем сериализуемые объекты между собой
        ObjectEncoderOutputStream oeos = null;  // stream netty
        ObjectDecoderInputStream odis = null;
        try (Socket socket = new Socket("localhost", 8188)) {
            oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
            MyMessage textMessage = new MyMessage("Hello Server!!!");
            oeos.writeObject(textMessage);
            oeos.flush();
            odis = new ObjectDecoderInputStream(socket.getInputStream());
            MyMessage msgFromServer = (MyMessage)odis.readObject();
            System.out.println("Answer from server: " + msgFromServer.getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oeos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                odis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
