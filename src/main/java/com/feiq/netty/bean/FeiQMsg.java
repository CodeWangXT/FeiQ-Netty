package com.feiq.netty.bean;

import com.feiq.netty.common.FeiQCommand;
import com.feiq.netty.util.FeiQDataUtil;
import lombok.Data;

/**
 * 保存飞秋格式化后数据信息<br/>
 * 飞秋UDP数据格式为：
 * Ver(1): PacketNo:SenderName:SenderHost:CommandNo:AdditionalSection
 * 1.Ver(1):版本号，默认为1
 * 2.PacketNo:数据包序列号,通常为毫秒级时间数
 * 3.SenderName:发送人的用户名(发送者的昵称)
 * 4.SenderHost:发送的主机名
 * 5.CommandNo:发送的命令
 * 6.AdditionalSection:不同的命令对应不同的数据
 *
 */
@Data
public class FeiQMsg {

    /**
     * 消息分割符
     */
    public static final String MSG_SPLIT_FLAG = ":";

    /**
     * 版本信息
     */
    private String version;

    /**
     * 包编号
     */
    private String packetNo;

    /**
     * 发送人昵称
     */
    private String senderName;

    /**
     * 发送主机名
     */
    private String senderHost;

    /**
     * 发送的命令
     */
    private int commandNo;

    /**
     * 附加信息，根据命令不同而不同
     */
    private String additionalSection;

    /**
     * 默认构造方法
     */
    public FeiQMsg(){

    }

    /**
     * 将传输过来的UDP数据包解析为响应的MSG消息
     * @param msgData UDP数据包
     */
    public FeiQMsg(String msgData){

        this.parseMsg(msgData);

    }

    /**
     * 封装对象构造方法
     * @param packetNo
     * @param commandNo
     * @param additionalSection
     */
    public FeiQMsg(String packetNo,int commandNo,String additionalSection){
        this.version = String.valueOf(FeiQCommand.IPMSG_VERSION);
        this.packetNo = packetNo;
        this.senderName = FeiQDataUtil.getSenderName();
        this.senderHost = FeiQDataUtil.getSenderHost();
        this.commandNo = commandNo;
        this.additionalSection = additionalSection;
    }

    /**
     * 拆分消息信息转换成具体数据信息
     * @param msgData 待解析的消息
     */
    private void parseMsg(String msgData){
        if(null == msgData){
            throw new IllegalArgumentException("FeqiQ Msg Data is null!");
        }
        String[] dataArray = msgData.split(MSG_SPLIT_FLAG,6);
        if(dataArray.length < 5){
            throw new IllegalArgumentException("FeqiQ Msg Data format error!");
        }
        this.version = dataArray[0];
        this.packetNo = dataArray[1];
        this.senderName = dataArray[2];
        this.senderHost = dataArray[3];
        this.commandNo = Integer.valueOf(dataArray[4]);
        if(dataArray.length >= 6){
            this.additionalSection = dataArray[5];
        }

    }

    /**
     * 构建发送消息
     * @return 发送字符串
     */
    public String buildSendMsg(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.version)
                .append(MSG_SPLIT_FLAG)
                .append(this.packetNo)
                .append(MSG_SPLIT_FLAG)
                .append(this.senderName)
                .append(MSG_SPLIT_FLAG)
                .append(this.senderHost)
                .append(MSG_SPLIT_FLAG)
                .append(this.commandNo);
        if(null != this.additionalSection && this.additionalSection.length() > 0){
            sb.append(MSG_SPLIT_FLAG).append(this.additionalSection);
        }
        return sb.toString();
    }




}
