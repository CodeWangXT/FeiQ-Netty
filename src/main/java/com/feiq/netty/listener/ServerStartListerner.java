package com.feiq.netty.listener;

import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.common.FeiQCommand;
import com.feiq.netty.common.ServerInfoCommon;
import com.feiq.netty.util.FeiQDataUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 飞秋服务器启动监听器，用于在服务启动后，广播我已上线消息，便于其他网络用户收到，并响应
 */
@Slf4j
public class ServerStartListerner {

    /**
     * 服务器启动后广播我上线消息
     * @param ch 创建Channel通道
     */
    public void run(Channel ch){
        log.info("FeiQ Server Start Boardcast I UP Msg Begin...");
        FeiQMsg feiQMsg = this.buildMsg();
        ch.writeAndFlush(FeiQDataUtil.buildBoardcastDatagramPacket(feiQMsg));
        log.info("FeiQ Server Start Boardcast I UP Msg End...");
    }

    /**
     * 构建上线消息
     * @return 飞秋对象信息
     */
    private FeiQMsg buildMsg(){
        FeiQMsg feiQMsg = new FeiQMsg(FeiQDataUtil.genPacketNo(), FeiQCommand.IPMSG_BR_ENTRY,null);
        return feiQMsg;
    }

}
