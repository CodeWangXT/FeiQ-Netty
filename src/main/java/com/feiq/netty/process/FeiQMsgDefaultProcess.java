package com.feiq.netty.process;

import com.feiq.netty.bean.FeiQMsg;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 飞秋消息默认实现消息处理
 */
@Slf4j
public class FeiQMsgDefaultProcess implements IFeiQMsgProcess {

    private static final FeiQMsgDefaultProcess instance = new FeiQMsgDefaultProcess();

    /**
     *
     * @return 处理单例
     */
    public static FeiQMsgDefaultProcess getInstance(){
        return instance;
    }

    /**
     * 处理飞秋消息入口，显示及存储消息
     *
     * @param feiQMsg 待处理的飞秋消息
     */
    public void msgProcess(ChannelHandlerContext ctx, FeiQMsg feiQMsg) {
        log.info("Recevice From {} FeiQMsg: {} ",feiQMsg.getSenderName(),feiQMsg);
    }
}
