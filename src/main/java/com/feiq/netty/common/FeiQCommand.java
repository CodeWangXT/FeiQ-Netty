package com.feiq.netty.common;

/**
 * FeiQ命令集常量
 */
public class FeiQCommand {
    /**
     * 版本信息，目前固定为1
     */
    public static final int IPMSG_VERSION = 1;

    /**
     * IPMSG_NOOPERATION:表示不进行任何操作
     */
    public static final int IPMSG_NOOPERATION = 0x00000000;

    /**
     * IPMSG_BR_ENTRY:表示用户上线（上线）
     */
    public static final int IPMSG_BR_ENTRY = 0x00000001;

    /**
     * IPMSG_BR_EXIT:表示用户退出（下线）
     */
    public static final int IPMSG_BR_EXIT = 0x00000002;

    /**
     * IPMSG_ANSENTRY:表示新用户通过IPMSG_ANSENTRY命令得到当前在线用户信息。所有用户在IP存在时可以互相通信
     */
    public static final int IPMSG_ANSENTRY = 0x00000003;

    /**
     * IPMSG_BR_ABSENCE:表示IPMSG_BR_ABSENCE信息广播给所有用户该用户离开状态取消或者昵称改变。但是和IPMSG_BR_ENTRY命令不同的是IPMSG_BR_ABSENCE命令不回发信息
     */
    public static final int IPMSG_BR_ABSENCE = 0x00000004;

    /**
     *IPMSG_BR_ISGETLIST:搜寻有效的主机用户
     */
    public static final int IPMSG_BR_ISGETLIST = 0x00000010;

    /**
     * IPMSG_OKGETLIST:主机列表发送通知
     */
    public static final int IPMSG_OKGETLIST = 0x00000011;

    /**
     * 主机列表发送请求
     */
    public static final int IPMSG_GETLIST = 0x00000012;

    /**
     * 主机列表发送
     */
    public static final int IPMSG_ANSLIST = 0x00000013;

    /**
     *
     */
    public static final int IPMSG_FILE_MTIME = 0x00000014;

    /**
     *
     */
    public static final int IPMSG_FILE_CREATETIME = 0x00000016;

    /**
     *
     */
    public static final int IPMSG_BR_ISGETLIST2 = 0x00000018;

    /**
     * IPMSG_SENDMSG:表示发送消息
     */
    public static final int IPMSG_SENDMSG = 0x00000020;

    /**
     * IPMSG_RECVMSG:表示接收到消息的响应
     */
    public static final int IPMSG_RECVMSG = 0x00000021;

    /**
     *消息打开通知,即已读通知
     */
    public static final int IPMSG_READMSG = 0x00000030;

    /**
     *消息丢弃通知
     */
    public static final int IPMSG_DELMSG = 0x00000031;

    /**
     * 离开状态（用户识别命令）
     */
    public static final int IPMSG_ABSENCEOPT = 0x00000100;

    /**
     *服务器（保留）
     */
    public static final int IPMSG_SERVEROPT = 0x00000200;

    /**
     *发送个人用户识别命令
     */
    public static final int IPMSG_DIALUPOPT = 0x00010000;

    /**
     *文件附件选项
     */
    public static final int IPMSG_FILEATTACHOPT = 0x00200000;

}
