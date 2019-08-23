package com.netty.servers.blockserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class FirstHandler extends ChannelInboundHandlerAdapter {
    // Handler может быть либо Inbound либо Outbound
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Получает ByteBuf
        // Через Inbound проходят request
        // Через Outbound проходят response
        ByteBuf buf = (ByteBuf)msg;
        if (buf.readableBytes() < 3) {
            return;
        }
        byte[] data = new byte[3];
        buf.readBytes(data);
        System.out.println(Arrays.toString(data));
        // пробрасываем дальше ByteBuf из трех байт
        ctx.fireChannelRead(data);

    }
}
