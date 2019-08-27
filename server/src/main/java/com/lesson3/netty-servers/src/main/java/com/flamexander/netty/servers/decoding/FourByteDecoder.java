package com.flamexander.netty.servers.decoding;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class FourByteDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // получение ByteBuf, нарезаем по 4-ре байта и формируем из них строку
        if (in.readableBytes() < 4) {
            return;
        }
        byte[] data = new byte[4];
        in.readBytes(data);
        String str = new String(data);
        // добавляем строку в List
        out.add(str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}