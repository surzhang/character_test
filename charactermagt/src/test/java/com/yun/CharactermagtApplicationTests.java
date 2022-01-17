package com.yun;

import com.yun.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CharactermagtApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private MailService mailService;

   /* @Test
    public void testSend() {
        String to = "3296252248@qq.com";
        mailService.send(to, "模板邮件", UUID.randomUUID().toString().toUpperCase());
    }*/

}
