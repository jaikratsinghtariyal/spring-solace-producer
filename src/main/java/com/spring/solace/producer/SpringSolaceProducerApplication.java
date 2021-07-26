package com.spring.solace.producer;

import com.solacesystems.jcsmp.JCSMPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringSolaceProducerApplication {

    @Autowired
    public DemoPublishEventHandler demoPublishEventHandler;

    public static void main(String[] args) {
        SpringApplication.run(SpringSolaceProducerApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam String message) throws JCSMPException {
        demoPublishEventHandler.publishMessage(message);
        return "Hello From Spring and Kubernetes example";
    }
}
