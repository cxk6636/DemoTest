package com.es;

/**
 * @author cxk
 * 2023/3/8 15:01
 */

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;

public class EsDemo {
    /**
     *
     * @return TransportClient
     * @throws Exception
     */
    public static final String IP ="10.10.5.23";
    public static final int PORT = 30029;
    @SuppressWarnings({"resource", "unchecked"})
    public static TransportClient getConnection() throws Exception {

        // 设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();//es为集群名称
        // 创建client
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName(IP), PORT));

        System.out.println("es连接成功，获取连接对象： " + client.toString());
        return client;
        //client.close(); 使用完要close断开连接，释放资源
    }
}