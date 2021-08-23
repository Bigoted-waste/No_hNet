package com.cola.noh.controller;


import com.cola.noh.service.RegisterService;
import com.cola.noh.service.ZookeeperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class RegisterController {

    @Value("${server.port}")
    private String serverPort;

//    @Autowired
//    private RegisterService registerService;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private ZookeeperServiceImpl zookeeperService;

    @RequestMapping("/register/register")
    public String registerAssembly(){
        return serverPort;
    }

    @GetMapping("/register/discovery")
    public Object discovery(){
        //获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services: "+services);

        //得到一个具体微服务信息,通过具体的微服务id， applicationName;
        List<ServiceInstance> instances = client.getInstances("Noh-register"); //Noh-register

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"
                            +instance.getPort()+"\t"
                            +instance.getUri()+"\t"
                            +instance.getServiceId()+"\t"
                            +instance.getInstanceId()+"\t"
                            +instance.getScheme()+"\t"
                            +instance.getMetadata()
            );
        }
        return this.client;
    }

    @GetMapping("/register/kickOut/{InstanceId}")
    public Object kickOut(@PathVariable("InstanceId") String InstanceId){
        if (!zookeeperService.kickOut(InstanceId)) {
            return "提出失败";
        }{
            return "踢出成功";
        }
    }
}
