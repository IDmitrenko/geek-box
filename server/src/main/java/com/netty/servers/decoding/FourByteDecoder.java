package com.netty.servers.decoding;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class FourByteDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int arrSize = 4;
        if (in.readableBytes() < arrSize)
        {
            arrSize = in.readableBytes();
        }
        byte[] data = new byte[arrSize];
        in.readBytes(data);
        // Преобразование Bytebuf в String (объект) по 4 байта
        String str = new String(data);
        // добавляем String в List<Object> объект список объектов по 4 байта (передача кусками)
        out.add(str);
    }
}