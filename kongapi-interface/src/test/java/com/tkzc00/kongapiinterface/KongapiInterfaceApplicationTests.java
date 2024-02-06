package com.tkzc00.kongapiinterface;

import com.tkzc00.kongapiinterface.client.KongApiClient;
import com.tkzc00.kongapiinterface.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KongapiInterfaceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testAccessKeySecretKey() {
        KongApiClient client = new KongApiClient("tkzc00", "abcdefgh");
        User user = new User();
        user.setUsername("tkzc00");
        System.out.println(client.getUserNameByPost(user));
    }
}
