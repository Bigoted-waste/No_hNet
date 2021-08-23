package com.cola.noh.service;

import org.springframework.stereotype.Service;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//@Service
public class RegisterServiceImpl implements RegisterService{

    /**
     * 127.xxx.xxx.xxx 属于"loopback" 地址，即只能你自己的本机可见，就是本机地址，比较常见的有127.0.0.1；
     * 192.168.xxx.xxx 属于private 私有地址(site local address)，属于本地组织内部访问，只能在本地局域网可见。同样10.xxx.xxx.xxx、从172.16.xxx.xxx 到 172.31.xxx.xxx都是私有地址，也是属于组织内部访问；
     * 169.254.xxx.xxx 属于连接本地地址（link local IP），在单独网段可用
     * 从224.xxx.xxx.xxx 到 239.xxx.xxx.xxx 属于组播地址
     * 比较特殊的255.255.255.255 属于广播地址
     * 除此之外的地址就是点对点的可用的公开IPv4地址
     * @return
     * @throws UnknownHostException
     *
     */
    @Override
    public List<String> getIpAddress()  {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipList;

    }

}
