package com.gupao.edu.vip.lion.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public final class Utils {
    private final static Logger LOGGER = LoggerFactory.getLogger(Utils.class);
    /**
     * 本地IP
     */
    private static String LOCAL_IP;
    /**
     * 外网IP
     */
    private static String EXTRANET_IP;
    /**
     * 只获取第一块网卡绑定的ip地址
     *
     * @param getLocal 局域网IP
     * @return ip
     */
    public static String getInetAddress(boolean getLocal) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                Enumeration<InetAddress> addresses = interfaces.nextElement().getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address.isLoopbackAddress()) continue;
                    if (address.getHostAddress().contains(":")) continue;
                    if (getLocal) {
                        if (address.isSiteLocalAddress()) {
                            return address.getHostAddress();
                        }
                    } else {
                        if (!address.isSiteLocalAddress()) {
                            return address.getHostAddress();
                        }
                    }
                }
            }
            LOGGER.debug("getInetAddress is null, getLocal={}", getLocal);
            return getLocal ? "127.0.0.1" : null;
        } catch (Throwable e) {
            LOGGER.error("getInetAddress exception", e);
            return getLocal ? "127.0.0.1" : null;
        }
    }

    public static String lookupLocalIp() {
        if (LOCAL_IP == null){
            LOCAL_IP = getInetAddress(true);
        }
        return LOCAL_IP;
    }

    public static String lookupExtranetIp() {
        if(EXTRANET_IP == null){
            EXTRANET_IP =getInetAddress(false);
        }
        return EXTRANET_IP;
    }
}
