package com.gupao.edu.vip.lion.bootstrap;

import com.gupao.edu.vip.lion.tools.log.Logs;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-05
 */
public class Main {
    public static void main(String[] args) {
        //初始化系统变量
        Logs.init();
        Logs.Console.info("launch Lion server...");
        ServerLauncher launcher = new ServerLauncher();
        launcher.init();
    }

}
