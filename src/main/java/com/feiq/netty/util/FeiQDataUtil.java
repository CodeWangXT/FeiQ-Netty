package com.feiq.netty.util;

import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.common.ServerInfoCommon;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.SocketUtils;

/**
 * 飞秋数据相关工具类
 */
public class FeiQDataUtil {

    /**
     * 飞秋包编号生成方法<br/>
     * 默认当前时间
     * @return 包编号
     */
    public static String genPacketNo(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取发送人昵称
     * @return 发送人昵称
     */
    public static String getSenderName(){
        return ServerInfoCommon.SERVER_IP;
    }

    /**
     * 获取发送主机名
     * @return 主机名
     */
    public static String getSenderHost(){
        return ServerInfoCommon.SERVER_HOST;
    }

    /**
     * 构建UDP数据包
     * @param feiQMsg 飞秋消息
     * @return UDP数据包对象
     */
    public static DatagramPacket buildBoardcastDatagramPacket(FeiQMsg feiQMsg){
        return buildDatagramPacket(feiQMsg, ServerInfoCommon.BROADCAST_IP_ADDR, ServerInfoCommon.FEIQ_SERVICE_DEFAULT_PORT);
    }

    /**
     * 构建UDP数据包
     * @param feiQMsg 飞秋消息
     * @param ip IP地址
     * @param port
     * @return
     */
    public static DatagramPacket buildDatagramPacket(FeiQMsg feiQMsg,String ip,int port){
        String sendMsg = feiQMsg.buildSendMsg();
        return new DatagramPacket(Unpooled.copiedBuffer(sendMsg, CharsetUtil.UTF_8), SocketUtils.socketAddress(ip,port));
    }
}
