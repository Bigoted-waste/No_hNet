package com.cola.noh;

import com.cola.noh.service.RegisterService;
import com.cola.noh.service.RegisterServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;
import java.util.List;

@SpringBootTest
class NohTest {

    @Test
    void getHost() throws UnknownHostException {
        RegisterServiceImpl registerService = new RegisterServiceImpl();
        List<String> ipAddress = registerService.getIpAddress();
        System.out.println(ipAddress);
    }
}
