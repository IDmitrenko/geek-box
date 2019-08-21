package com.cloud.server;

import com.cloud.common.transfer.AuthMessage;
import com.cloud.common.transfer.CommandMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthGatewayHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LogManager.getLogger(AuthGatewayHandler.class.getName());

    private boolean authorized;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Подключился новый авторизованный клиент.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }
        if (!authorized) {
            if (msg instanceof AuthMessage) {
                AuthMessage am = (AuthMessage) msg;
                if (am.getLogin().equals("login") && am.getPassword().equals("password")) {
                    String username = "client";
                    authorized = true;
                    CommandMessage cmAuthOk = new CommandMessage(CommandMessage.CMD_MSG_AUTH_OK);
                    ChannelFuture future = ctx.writeAndFlush(cmAuthOk);
                    future.await();
                    ctx.pipeline().addLast(new ServerHandler(username));
//                    ServerUtilities.sendFileList(ctx.channel(), username);
                }
            } else {
                ReferenceCountUtil.release(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
