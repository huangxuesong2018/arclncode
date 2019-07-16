package com.gupao.edu.vip.lion.tools.config;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public final class ConfigTools {
    public static String getConnectServerRegisterIp() {
        if (CC.lion.net.connect_server_register_ip.length() > 0) {
            return CC.lion.net.connect_server_register_ip;
        }
        return getPublicIp();
    }
}
