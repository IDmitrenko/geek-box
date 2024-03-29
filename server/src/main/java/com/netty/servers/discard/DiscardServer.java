package com.netty.servers.discard;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
    public void run() throws Exception {
        // создаем два пула потоков
        // Обработка подключений
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // обработка поступающей информации
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // Настройка сервера
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // выстраиваем конвеер
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        // прописываем какой handler используем
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // хотим держать соединение открытым
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(8188).sync();
            // ожидание завершения сервера
            f.channel().closeFuture().sync();
            // после завершения сервера переходим к finally
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new DiscardServer().run();
    }
}
