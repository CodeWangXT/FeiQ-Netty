package com.feiq.netty.handler;

import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.common.ServerInfoCommon;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;


/**
 * 飞秋接收到的UDP数据包解析
 */
@Slf4j
public class FeiQInputDataParse extends ChannelInboundHandlerAdapter {

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DatagramPacket feiQMsg = (DatagramPacket)msg;
        //如果自己发送的则中断处理链，消息丢弃
        if(this.checkIsSelfSender(feiQMsg)){
            return;
        }
        log.info("Recevice From {} Data",ctx.channel().remoteAddress());
        final String content = feiQMsg.content().toString(CharsetUtil.UTF_8);
        //将消息封装成FeiQMsg传给下个处理器处理封装好的飞秋消息
        ctx.fireChannelRead(new FeiQMsg(content));
    }

    /**
     * 由于广播通知自己会收到自己发送的消息，故如果自己发送的消息就丢弃，正常不会自己给自己专门发送消息
     * @return 如果是自己返回true，否则返回false
     */
    private boolean checkIsSelfSender(DatagramPacket feiQMsg){
        InetSocketAddress sender = feiQMsg.sender();
        /**
         * 如果IP与端口一样说明是自己发送
         */
        if(ServerInfoCommon.SERVER_IP.equals(sender.getAddress().getHostAddress().toString()) && ServerInfoCommon.FEIQ_SERVICE_DEFAULT_PORT == sender.getPort()){
            return true;
        }
        return false;

    }

}
