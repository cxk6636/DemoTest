package com.java;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;

public class As3Demo {
    public static void main(String[] args) {
        AmazonS3 as3 = new AmazonS3Client(new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"));
        Region usWest2 = Region.getRegion(Regions.CN_NORTH_1);
        as3.setRegion(usWest2);
        as3.setEndpoint("http://10.10.35.239:30009");
        String s3BackupPath = "redis-cluster/redis-86/jiqun02";
        ObjectListing objectListing = as3.listObjects("zstest", s3BackupPath);
        System.out.println();
    }
}
