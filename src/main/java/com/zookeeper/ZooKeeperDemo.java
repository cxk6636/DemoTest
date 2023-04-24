package com.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZooKeeperDemo implements Watcher {

    private static final int SESSION_TIMEOUT = 5000;
    private ZooKeeper zk;

    public void connect(String host) throws IOException, InterruptedException {
        zk = new ZooKeeper(host, SESSION_TIMEOUT, this);

        synchronized (zk) {
            zk.wait();
        }
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            synchronized (zk) {
                zk.notifyAll();
            }
        }
    }

    public void createNode(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zk.delete(path, -1);
    }

    public byte[] getData(String path, boolean watch) throws KeeperException, InterruptedException {
        return zk.getData(path, watch, null);
    }

    public void setData(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.setData(path, data, -1);
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception {
        ZooKeeperDemo demo = new ZooKeeperDemo();
        demo.connect("localhost:2181");

        // Create a node
        String path = "/my-node";
        byte[] data = "Hello, ZooKeeper!".getBytes();
        demo.createNode(path, data);

        // Get node data
        byte[] retrievedData = demo.getData(path, true);
        System.out.println("Retrieved data: " + new String(retrievedData));

        // Set node data
        byte[] newData = "Hello again, ZooKeeper!".getBytes();
        demo.setData(path, newData);

        // Delete node
        demo.deleteNode(path);

        demo.close();
    }
}