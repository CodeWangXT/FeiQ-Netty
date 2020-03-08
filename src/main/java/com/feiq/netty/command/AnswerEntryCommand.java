package com.feiq.netty.command;


import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.common.FeiQCommand;
import com.feiq.netty.process.IFeiQMsgProcess;
import com.feiq.netty.util.FeiQDataUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应用户上线广播消息<br/>
 */
@Slf4j
public class AnswerEntryCommand implements IFeiQMsgProcess {

    /**
     * 处理飞秋消息入口，显示及存储消息
     * @param ctx 管道信息
     * @param feiQMsg 待处理的飞秋消息
     */
    public void msgProcess(ChannelHandlerContext ctx,FeiQMsg feiQMsg) {
        final FeiQMsg answerEntryMsg = this.buildAnswerEntryMsg();
        ctx.writeAndFlush(FeiQDataUtil.buildBoardcastDatagramPacket(answerEntryMsg));
    }

    /**
     * 构建消息响应信息
     * @return
     */
    private FeiQMsg buildAnswerEntryMsg(){
        return new FeiQMsg(FeiQDataUtil.genPacketNo(), FeiQCommand.IPMSG_ANSENTRY,null);
    }
}
