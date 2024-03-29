package com.flamexander.netty.servers.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ProtocolHandler extends ChannelInboundHandlerAdapter {
    private enum DataType {
        EMPTY((byte)-1), FILE((byte)15), COMMAND((byte)16);

        byte firstMessageByte;

        DataType(byte firstMessageByte) {
            this.firstMessageByte = firstMessageByte;
        }

        static DataType getDataTypeFromByte(byte b) {
            if (b == FILE.firstMessageByte) {
                return FILE;
            }
            if (b == COMMAND.firstMessageByte) {
                return COMMAND;
            }
            return EMPTY;
        }
    }

    private int state = -1;
    private int reqLen = -1;
    private DataType type = DataType.EMPTY;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // обработка собственного протокола
        // накапливаем данные, анализируем их, и обрабатываем.
        ByteBuf buf = ((ByteBuf) msg);

        if (state == -1) {
            byte firstByte = buf.readByte();
            // считываем первый байт
            type = DataType.getDataTypeFromByte(firstByte);
            state = 0;
            // далее по логике указываем что надо следующие 4-ре байта
            reqLen = 4;
            System.out.println(type);
        }

        if (state == 0) {
            if (buf.readableBytes() < reqLen) {
                return;
            }
            // считываем int из буфера
            reqLen = buf.readInt();
            state = 1;
            System.out.println("text size: " + reqLen);
        }

        if (state == 1) {
            if (buf.readableBytes() < reqLen) {
                return;
            }
            // ждем пока в буфере появится сообщение нужной длины
            byte[] data = new byte[reqLen];
            buf.readBytes(data);
            String str = new String(data);
            System.out.println(type + " " + str);
            state = -1;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}