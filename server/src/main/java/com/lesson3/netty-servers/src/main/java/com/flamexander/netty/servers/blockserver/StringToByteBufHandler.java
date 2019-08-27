package com.flamexander.netty.servers.blockserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;

public class StringToByteBufHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String str = (String)msg;
        byte[] arr = str.getBytes();
        // создаем буфер через специальный выделитель памяти
        ByteBuf buf = ctx.alloc().buffer(arr.length);
        // записали в буфер
        buf.writeBytes(arr);
        // вернули ByteBuf клиенту
        ctx.writeAndFlush(buf);
        buf.release();
    }
}
