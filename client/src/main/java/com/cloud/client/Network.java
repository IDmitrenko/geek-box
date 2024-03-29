package com.cloud.client;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Network {
    public static Network ourInstance = new Network();

    public static Network getOurInstance() {
        return ourInstance;
    }

    private Socket socket;
    private ObjectEncoderOutputStream out;
    private ObjectDecoderInputStream in;

    public ObjectEncoderOutputStream getOut() {
        return out;
    }

    boolean isConnected(){
        return socket!=null && !socket.isClosed();
    }

    public Network() {
    }

    public void connect() throws IOException {
        this.socket = new Socket("localhost",8189);
        in = new ObjectDecoderInputStream(socket.getInputStream());
        out = new ObjectEncoderOutputStream(socket.getOutputStream());
    }

    public void sendData(Object data) throws IOException {
        out.writeObject(data);
        out.flush();
    }

    public Object readData() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public void close(){
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}