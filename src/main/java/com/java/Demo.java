package com.java;


public class Demo {

    public static void main(String[] args) {
        String input = "{\"accessKeyId\":\"AKIAIOSFODNN7EXAMPLE\",\"accessKeySecret\":\"wJalrXUtnFEMIK7MDENGbPxRfiCYEXAMPLEKEY\",\"backupFileName\":\"redisbackup-cluster-job-20230610144508\",\"backupRate\":\"\",\"bucket\":\"bucket3\",\"dayOfWeek\":\"\",\"days\":\"\",\"deployId\":117,\"endpoint\":\"http:10.10.35.239:30009\",\"operationalAudit\":{\"operationExample\":\"jiqun04\",\"operationId\":\"117\",\"operationModule\":\"redis\"},\"provider\":\"minio239\",\"region\":\"default\",\"startTime\":\"2023-06-10 14:45:09\",\"storageId\":1}";
        input = input.replace("\"accessKeyId\":\"" + input.split("\"accessKeyId\":\"")[1].split("\",\"")[0] + "\"", "\"accessKeyId\":\"**********\"");
        input = input.replace("\"accessKeySecret\":\"" + input.split("\"accessKeySecret\":\"")[1].split("\",\"")[0] + "\"", "\"accessKeySecret\":\"**********\"");
        System.out.println(input);
    }
}
