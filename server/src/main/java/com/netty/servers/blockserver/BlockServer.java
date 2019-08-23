package com.netty.servers.blockserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BlockServer
{
  public void run() throws Exception
  {
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try
    {
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>()
      { // (4)
        @Override
        public void initChannel(SocketChannel ch) throws Exception
        {
          ch.pipeline()
                  // ответы могут отправить только InboundHandler стоящие после OutboundHandler
            .addLast(new FirstHandler(), new SecondHandler(), new GatewayHandler(),
                    new StringToByteBufHandler(), new FinalHandler());
        }
      }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
      ChannelFuture f = b.bind(8188).sync();
      f.channel().closeFuture().sync();
    }
    finally
    {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws Exception
  {
    new BlockServer().run();
  }
}
