package com.feiq.netty.listener;

import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.common.FeiQCommand;
import com.feiq.netty.util.FeiQDataUtil;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务器关闭时广播下线通知
 */
@Slf4j
public class ServerCloseListerner implements GenericFutureListener<ChannelFuture> {
    /**
     * Invoked when the operation associated with the {@link Future} has been completed.
     *
     * @param future the source {@link Future} which called this callback
     */
    public void operationComplete(ChannelFuture future) throws Exception {
        log.info("Server Close Listener Boardcast Exit begin...");
        future.channel().writeAndFlush(FeiQDataUtil.buildBoardcastDatagramPacket(buildMsg()));
        log.info("Server Close Listener Boardcast Exit end...");
    }

    /**
     * 构建离线广播消息
     * @return 构建飞秋消息
     */
    private FeiQMsg buildMsg(){
        return new FeiQMsg(FeiQDataUtil.genPacketNo(), FeiQCommand.IPMSG_BR_EXIT,null);
    }
}
