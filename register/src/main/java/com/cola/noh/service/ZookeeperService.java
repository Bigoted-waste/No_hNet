package com.cola.noh.service;

public interface ZookeeperService {
    void getChild();    //获取节点信息

    void exist(String path);       //判断节点是否存在

    boolean kickOut(String InstanceId);     //提出节点

}
