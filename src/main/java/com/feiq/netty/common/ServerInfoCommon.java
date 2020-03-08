package com.feiq.netty.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 主要用于记录服务相关的通信常量
 */
public class ServerInfoCommon {
    /**
     * 飞秋默认的服务UDP通信端口是2425
     */
    public static final int FEIQ_SERVICE_DEFAULT_PORT = 2425;

    /**
     * 默认的广播地址
     */
    public static final String BROADCAST_IP_ADDR = "255.255.255.255";

    /**
     * 主机昵称
     */
    public static String SERVER_IP = null;

    /**
     * 主机名
     */
    public static String SERVER_HOST = null;

    static {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            SERVER_IP = localHost.getHostAddress().toString();
            SERVER_HOST = localHost.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


}
