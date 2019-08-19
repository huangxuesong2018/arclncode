package com.gupao.edu.vip.lion.tools.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-25
 */
public interface CC {
    Config cfg = load();
    static Config load(){
        Config config = ConfigFactory.load();//扫描加载所有可用的配置文件
        String custom_conf = "lion.conf";//加载自定义配置, 值来自jvm启动参数指定-Dlion.conf
        if (config.hasPath(custom_conf)) {
            File file = new File(config.getString(custom_conf));
            if (file.exists()) {
                Config custom = ConfigFactory.parseFile(file);
                config = custom.withFallback(config);
            }
        }
        return config;
    }
    interface lion {
        Config cfg = CC.cfg.getObject("lion").toConfig();
        String log_dir = cfg.getString("log-dir");
        String log_level = cfg.getString("log-level");
        String log_conf_path = cfg.getString("log-conf-path");

        interface core {
            Config cfg = lion.cfg.getObject("core").toConfig();

            int session_expired_time = (int) cfg.getDuration("session-expired-time").getSeconds();

            int max_heartbeat = (int) cfg.getDuration("max-heartbeat", TimeUnit.MILLISECONDS);

            int max_packet_size = (int) cfg.getMemorySize("max-packet-size").toBytes();

            int min_heartbeat = (int) cfg.getDuration("min-heartbeat", TimeUnit.MILLISECONDS);

            long compress_threshold = cfg.getBytes("compress-threshold");

            int max_hb_timeout_times = cfg.getInt("max-hb-timeout-times");

            String epoll_provider = cfg.getString("epoll-provider");

            static boolean useNettyEpoll() {
                if (!"netty".equals(lion.core.epoll_provider)) return false;
                String name = CC.cfg.getString("os.name").toLowerCase(Locale.UK).trim();
                return name.startsWith("linux");//只在linux下使用netty提供的epoll库
            }
        }

        interface net {
            Config cfg = lion.cfg.getObject("net").toConfig();

            String local_ip = cfg.getString("local-ip");
            String public_ip = cfg.getString("public-ip");

            int connect_server_port = cfg.getInt("connect-server-port");
            String connect_server_bind_ip = cfg.getString("connect-server-bind-ip");
            String connect_server_register_ip = cfg.getString("connect-server-register-ip");
            Map<String, Object> connect_server_register_attr = cfg.getObject("connect-server-register-attr").unwrapped();


            int admin_server_port = cfg.getInt("admin-server-port");

            int gateway_server_port = cfg.getInt("gateway-server-port");
            String gateway_server_bind_ip = cfg.getString("gateway-server-bind-ip");
            String gateway_server_register_ip = cfg.getString("gateway-server-register-ip");
            String gateway_server_net = cfg.getString("gateway-server-net");
            String gateway_server_multicast = cfg.getString("gateway-server-multicast");
            String gateway_client_multicast = cfg.getString("gateway-client-multicast");
            int gateway_client_port = cfg.getInt("gateway-client-port");

            int ws_server_port = cfg.getInt("ws-server-port");
            String ws_path = cfg.getString("ws-path");
            int gateway_client_num = cfg.getInt("gateway-client-num");

            static boolean tcpGateway() {
                return "tcp".equals(gateway_server_net);
            }

            static boolean udpGateway() {
                return "udp".equals(gateway_server_net);
            }

            static boolean wsEnabled() {
                return ws_server_port > 0;
            }

            static boolean udtGateway() {
                return "udt".equals(gateway_server_net);
            }

            static boolean sctpGateway() {
                return "sctp".equals(gateway_server_net);
            }


            interface public_ip_mapping {

                Map<String, Object> mappings = net.cfg.getObject("public-host-mapping").unwrapped();

                static String getString(String localIp) {
                    return (String) mappings.get(localIp);
                }
            }

            interface snd_buf {
                Config cfg = net.cfg.getObject("snd_buf").toConfig();
                int connect_server = (int) cfg.getMemorySize("connect-server").toBytes();
                int gateway_server = (int) cfg.getMemorySize("gateway-server").toBytes();
                int gateway_client = (int) cfg.getMemorySize("gateway-client").toBytes();
            }

            interface rcv_buf {
                Config cfg = net.cfg.getObject("rcv_buf").toConfig();
                int connect_server = (int) cfg.getMemorySize("connect-server").toBytes();
                int gateway_server = (int) cfg.getMemorySize("gateway-server").toBytes();
                int gateway_client = (int) cfg.getMemorySize("gateway-client").toBytes();
            }

            interface write_buffer_water_mark {
                Config cfg = net.cfg.getObject("write-buffer-water-mark").toConfig();
                int connect_server_low = (int) cfg.getMemorySize("connect-server-low").toBytes();
                int connect_server_high = (int) cfg.getMemorySize("connect-server-high").toBytes();
                int gateway_server_low = (int) cfg.getMemorySize("gateway-server-low").toBytes();
                int gateway_server_high = (int) cfg.getMemorySize("gateway-server-high").toBytes();
            }

            interface traffic_shaping {
                Config cfg = net.cfg.getObject("traffic-shaping").toConfig();

                interface gateway_client {
                    Config cfg = traffic_shaping.cfg.getObject("gateway-client").toConfig();
                    boolean enabled = cfg.getBoolean("enabled");
                    long check_interval = cfg.getDuration("check-interval", TimeUnit.MILLISECONDS);
                    long write_global_limit = cfg.getBytes("write-global-limit");
                    long read_global_limit = cfg.getBytes("read-global-limit");
                    long write_channel_limit = cfg.getBytes("write-channel-limit");
                    long read_channel_limit = cfg.getBytes("read-channel-limit");
                }

                interface gateway_server {
                    Config cfg = traffic_shaping.cfg.getObject("gateway-server").toConfig();
                    boolean enabled = cfg.getBoolean("enabled");
                    long check_interval = cfg.getDuration("check-interval", TimeUnit.MILLISECONDS);
                    long write_global_limit = cfg.getBytes("write-global-limit");
                    long read_global_limit = cfg.getBytes("read-global-limit");
                    long write_channel_limit = cfg.getBytes("write-channel-limit");
                    long read_channel_limit = cfg.getBytes("read-channel-limit");
                }

                interface connect_server {
                    Config cfg = traffic_shaping.cfg.getObject("connect-server").toConfig();
                    boolean enabled = cfg.getBoolean("enabled");
                    long check_interval = cfg.getDuration("check-interval", TimeUnit.MILLISECONDS);
                    long write_global_limit = cfg.getBytes("write-global-limit");
                    long read_global_limit = cfg.getBytes("read-global-limit");
                    long write_channel_limit = cfg.getBytes("write-channel-limit");
                    long read_channel_limit = cfg.getBytes("read-channel-limit");
                }
            }
        }
    }


}
