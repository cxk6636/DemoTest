package com.minio;
import io.minio.*;

public class MinioDemo {

    public static void main(String[] args) throws Exception {
        // 访问MinIO的访问密钥和密钥ID
        String accessKey = "minio";
        String secretKey = "Aa1234567";
        // MinIO的服务端点
        String endPoint = "https://10.10.5.23:31764";

        // 初始化MinIO客户端
        MinioClient minioClient = new MinioClient.Builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();

        System.out.println(minioClient);


    }
}