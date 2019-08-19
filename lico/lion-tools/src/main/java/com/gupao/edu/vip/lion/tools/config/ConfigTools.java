package com.gupao.edu.vip.lion.tools.config;

import com.gupao.edu.vip.lion.tools.Utils;

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


    /**
     * 获取外网IP地址
     *
     * @return 外网IP地址
     */
    public static String getPublicIp() {

        if (CC.lion.net.public_ip.length() > 0) {
            return CC.lion.net.public_ip;
        }

        String localIp = getLocalIp();

        String remoteIp = CC.lion.net.public_ip_mapping.getString(localIp);

        if (remoteIp == null) {
            remoteIp = Utils.lookupExtranetIp();
        }

        return remoteIp == null ? localIp : remoteIp;
    }

    public static String getLocalIp() {
        if (CC.lion.net.local_ip.length() > 0) {
            return CC.lion.net.local_ip;
        }
        return Utils.lookupLocalIp();
    }


}
