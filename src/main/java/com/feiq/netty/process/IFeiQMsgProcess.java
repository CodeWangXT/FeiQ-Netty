package com.feiq.netty.process;

import com.feiq.netty.bean.FeiQMsg;
import io.netty.channel.ChannelHandlerContext;

/**
 * 飞秋消息处理类
 */
public interface IFeiQMsgProcess {

    /**
     *处理飞秋消息入口，显示及存储消息
     * @param ctx 通道上下文信息
     * @param feiQMsg 待处理的飞秋消息
     */
    void msgProcess(ChannelHandlerContext ctx,FeiQMsg feiQMsg);
}
