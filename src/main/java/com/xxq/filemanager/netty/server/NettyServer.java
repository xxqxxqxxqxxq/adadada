package com.xxq.filemanager.netty.server;

import com.xxq.filemanager.netty.initializer.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * @ClassName NettyServer
 * @Description: TODO
 * @Author xxq
 * @Date 2019/12/26 14:52
 * @Version V1.0
 **/
public class NettyServer {
    public static Logger logger = LoggerFactory.getLogger(NettyServer.class);
    private int port=8081;
    public void start() {
        //new 一个主线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //new 一个工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup(200);
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer())
                //设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 两小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        //绑定端口,开始接收进来的连接
        try {
            ChannelFuture future = bootstrap.bind(new InetSocketAddress("127.0.0.1",port)).sync();
//            future.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                    if(future.isSuccess()) {
//                        System.out.println( ": 端口["+ port + "]绑定成功!");
//                    } else{
//                        System.err.println("端口["+ port + "]绑定失败!");
//                    }
//                }
//            });
            future.addListener(
                    future1 ->{
                        if(future1.isSuccess()) {

                            System.out.println(new Date()+": 端口["+ port + "]绑定成功!");

                        } else{

                            System.err.println("端口["+ port + "]绑定失败!");

                        }
                    }
            );
            logger.info("服务器启动开始监听端口: {}");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭主线程组
            bossGroup.shutdownGracefully();
            //关闭工作线程组
            workGroup.shutdownGracefully();
        }
    }
}
