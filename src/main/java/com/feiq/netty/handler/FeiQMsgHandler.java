package com.feiq.netty.handler;

import com.feiq.netty.bean.FeiQMsg;
import com.feiq.netty.command.FeiQMsgCommandFactory;
import com.feiq.netty.process.FeiQMsgDefaultProcess;
import com.feiq.netty.process.IFeiQMsgProcess;
import io.netty.channel.*;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * 飞秋消息处理，显示并保存数据等操作
 */
@Slf4j
public class FeiQMsgHandler extends ChannelInboundHandlerAdapter {

    /**
     * 默认异步处理的线程池
     */
    private static EventExecutorGroup executors = new DefaultEventExecutorGroup(20);

    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        final FeiQMsg feiQMsg = (FeiQMsg)msg;
        log.info("FeiQ Msg Handler starting... msg:{}",feiQMsg);
        //直接调用线程示例
//        ctx.channel().eventLoop().execute(new Thread(){
//
//        });
        executors.submit(new Thread(){
            @Override
            public void run(){
                IFeiQMsgProcess feiQMsgProcess = FeiQMsgCommandFactory.createCommandProcess(feiQMsg.getCommandNo());
                feiQMsgProcess.msgProcess(ctx,feiQMsg);
            }

        });
//        IFeiQMsgProcess feiQMsgProcess = FeiQMsgDefaultProcess.getInstance();
//        feiQMsgProcess.msgProcess(ctx,feiQMsg);
        ctx.fireChannelRead(msg);
    }
}
