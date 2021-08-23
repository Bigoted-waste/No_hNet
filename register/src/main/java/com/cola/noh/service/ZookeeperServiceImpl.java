package com.cola.noh.service;

import com.cola.noh.dao.ZookeeperDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperServiceImpl implements ZookeeperService{

    @Autowired
    private ZookeeperDaoImpl zookeeperDao;

    private static final String NOH_REGISTER_PATH="/services/Noh-register/";

    @Override
    public void getChild() {
        zookeeperDao.getMessage();
    }

    @Override
    public void exist(String path) {
        zookeeperDao.exitsNode(path);
    }

    /**
     * 踢出主机
     * @param InstanceId
     */
    @Override
    public boolean kickOut(String InstanceId) {
        return zookeeperDao.deleteNode(NOH_REGISTER_PATH + InstanceId);
    }

}
