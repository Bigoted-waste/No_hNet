package com.cola.noh.dao;

import java.util.List;

public interface ZookeeperDao {
    List<String> getMessage();
    boolean deleteNode(String path);
    boolean exitsNode(String path);
}
