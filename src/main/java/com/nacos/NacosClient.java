package com.nacos;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;

public class NacosClient {
    public static void main(String[] args) throws Exception {
        String serverAddr = "localhost:8848"; // Nacos Server 地址
        String dataId = "example"; // 配置 ID
        String group = "DEFAULT_GROUP"; // 配置分组

        ConfigService configService = ConfigFactory.createConfigService(serverAddr);
        String content = configService.getConfig(dataId, group, 5000); // 获取配置内容
        System.out.println(content);
    }
}