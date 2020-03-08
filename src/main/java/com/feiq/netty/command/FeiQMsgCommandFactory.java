package com.feiq.netty.command;

import com.feiq.netty.common.FeiQCommand;
import com.feiq.netty.process.IFeiQMsgProcess;

import java.util.HashMap;
import java.util.Map;

/**
 * 飞秋消息命令工程类<br/>
 * 根据命令返回不同的处理类
 */
public class FeiQMsgCommandFactory {

    /**
     * 保存命令与处理类映射
     */
    private static final Map<Integer,IFeiQMsgProcess> COMMOND_PROCESSES = new HashMap<Integer,IFeiQMsgProcess>();

    static {
        //广播上线处理类
        COMMOND_PROCESSES.put(FeiQCommand.IPMSG_BR_ENTRY,new AnswerEntryCommand());

    }



    /**
     * 根据命令创建对应的处理类
     * @param command 待处理的命令
     * @return 消息处理类
     */
    public static IFeiQMsgProcess createCommandProcess(int command){
        return COMMOND_PROCESSES.get(command);
    }
}
