package com.feiq.netty.server;

import com.feiq.netty.handler.FeiQInputDataParse;
import com.feiq.netty.handler.FeiQMsgHandler;
import com.feiq.netty.listener.ServerCloseListerner;
import com.feiq.netty.listener.ServerStartListerner;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 飞秋服务器对象<br/>
 * 通过Netty构建基于UDP的飞秋通信协议服务
 */
@Slf4j
public class FeiQServer {
    /**
     * 服务端口
     */
    private int port;

    public FeiQServer(int port){
        this.port = port;
    }

    /**
     * 服务调用入口
     */
    public void run() throws InterruptedException {
        log.info("FeiQ Server Starting  By Port {}.... ",this.port);
        NioEventLoopGroup server = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(server)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new ChannelInitializer<DatagramChannel>() {
                        @Override
                        protected void initChannel(DatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new FeiQInputDataParse());
                            ch.pipeline().addLast(new FeiQMsgHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(this.port).sync();
            log.info("FeiQ Server Start successfully !!!");
            new ServerStartListerner().run(future.channel());
            log.info("FeiQ Server Boardcast UP successfully !!!");
            ChannelFuture closeFuture = future.channel().closeFuture();
            //添加服务关闭监听器，用于下线时广播通知
            closeFuture.addListener(new ServerCloseListerner());
            closeFuture.await();
        }finally{
            server.shutdownGracefully();
            log.info("FeiQ Server ShutDown!!!");
        }

    }
}
