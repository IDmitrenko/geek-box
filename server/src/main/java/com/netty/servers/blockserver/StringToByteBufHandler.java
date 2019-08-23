package com.netty.servers.blockserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;

public class StringToByteBufHandler extends ChannelOutboundHandlerAdapter {
    // информация идет от клиента к серверу (для отправки ответа ByteBuf)
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String str = (String)msg;
        byte[] arr = str.getBytes();
        ByteBufAllocator al = new PooledByteBufAllocator();
        ByteBuf buf = al.buffer(arr.length);
        // преобразование ответа в ByteBuf
        buf.writeBytes(arr);
        ctx.writeAndFlush(buf);
    }
}
