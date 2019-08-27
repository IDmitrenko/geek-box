package com.flamexander.netty.example.server;

import com.flamexander.netty.example.common.AbstractMessage;
import com.flamexander.netty.example.common.FileMessage;
import com.flamexander.netty.example.common.FileRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof FileRequest) {
                FileRequest fr = (FileRequest) msg;
                // ищем файл на сервере
                if (Files.exists(Paths.get("server_storage/" + fr.getFilename()))) {
                    // создаем класс для передачи данных файла (в конструктор путь к этому файлу)
                    FileMessage fm = new FileMessage(Paths.get("server_storage/" + fr.getFilename()));
                    // передача файла клиенту
                    ctx.writeAndFlush(fm);
                }
            }
            if (msg instanceof FileMessage) {
                // Что делать если прилетел файл ??
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
