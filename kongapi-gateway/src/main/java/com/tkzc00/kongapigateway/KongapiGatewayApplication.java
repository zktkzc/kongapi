package com.tkzc00.kongapigateway;

import com.tkzc00.kongapibackend.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableDubbo
@Service
public class KongapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KongapiGatewayApplication.class, args);
        KongapiGatewayApplication application = context.getBean(KongapiGatewayApplication.class);
        System.out.println(application.doSayHello("tkzc00"));
        System.out.println(application.doSayHello2("tkzc00"));
    }

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

    public String doSayHello2(String name) {
        return demoService.sayHello2(name);
    }


}
