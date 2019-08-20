package com.cloud.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class.getName());

    private int propPort = 8188;
    private int propMaxObjSize = 1024 * 1024 * 100;

    public Server() {

    }

    public void run() throws Exception {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            try (Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/server.properties"))) {
                Properties pr = new Properties();
                pr.load(in);
                propPort = Integer.parseInt(pr.getProperty("port"));
                propMaxObjSize = Integer.parseInt(pr.getProperty("max_obj_size"));
            } catch (Exception ex) {
                logger.error("ОШИБКА: Файл свойств отсутствует!");
            }
            ServerBootstrap b = new ServerBootstrap();
            b.group(mainGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>()  {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectDecoder(propMaxObjSize, ClassResolvers.cacheDisabled(null)),
                                    new ObjectEncoder(),
                                    new AuthGatewayHandler() /* new ServerHandler("client")*/
                            );
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            logger.info("Server started at port " + propPort);
            logger.trace("trace");
            ChannelFuture future = b.bind(propPort).sync();
            future.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new Server().run();
    }
}
