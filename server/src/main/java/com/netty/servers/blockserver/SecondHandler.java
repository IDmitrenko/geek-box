package com.netty.servers.blockserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class SecondHandler extends ChannelInboundHandlerAdapter {
    // второй по счету InboundHandler
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] arr = (byte[])msg;
        // увеличили на 1 каждое значение массива
        for (int i = 0; i < 3; i++) {
            arr[i]++;
        }
        System.out.println("Второй шаг: " + Arrays.toString(arr));
        // передаем следующему InboundHandler
        ctx.fireChannelRead(arr);
    }
}
