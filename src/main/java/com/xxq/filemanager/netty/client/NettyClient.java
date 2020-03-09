package com.xxq.filemanager.netty.client;

import com.xxq.filemanager.netty.initializer.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName NettyClient
 * @Description: TODO
 * @Author xxq
 * @Date 2019/12/26 18:45
 * @Version V1.0
 **/
public class NettyClient {
    public static void sendMessage(String content) throws InterruptedException{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientInitializer());

            ChannelFuture future = b.connect("127.0.0.1", 8090).sync();
            // 发送消息
            future.channel().writeAndFlush(content);
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
