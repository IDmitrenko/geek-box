package com.netty.servers.blockserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Arrays;

public class GatewayHandler extends ChannelInboundHandlerAdapter {
    // третий по счету InboundHandler
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        int sum = 0;
        byte[] arr = (byte[]) msg;
        for (int i = 0; i < 3; i++) {
            sum += arr[i];
        }
        // Проверка достоверности информации
        if (sum == 66) {
            ctx.fireChannelRead(arr);
        } else {
            System.out.println("Сообщение сломано: " + Arrays.toString(arr));
        }
    }
}
