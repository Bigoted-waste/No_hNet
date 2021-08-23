package com.cola.noh.dao;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ZookeeperDaoImpl implements ZookeeperDao {
    private static String zookeeperAddress="192.168.56.31:2181,192.168.56.32:2181,192.168.56.33:2181";
    private static int sessionTimeout=2000;
    private ZooKeeper zkClient=null;


    public ZookeeperDaoImpl() {
        try {
            zkClient = new ZooKeeper(zookeeperAddress, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getMessage() {
        try {
            List<String> children = zkClient.getChildren("/services/Noh-register", false);
            return children;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteNode(String path) {
        try {
            zkClient.delete(path,-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exitsNode(String path) {
        try {
            Stat exists = zkClient.exists(path, false);
            return exists==null?false:true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
