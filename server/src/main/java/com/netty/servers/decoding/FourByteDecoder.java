package com.netty.servers.decoding;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class FourByteDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4)
        {
            return;
        }
        byte[] data = new byte[4];
        in.readBytes(data);
        // Преобразование Bytebuf в String (объект) по 4 байта
        String str = new String(data);
        // добавляем String в List<Object> объект список объектов по 4 байта (передача кусками)
        out.add(str);
    }
}