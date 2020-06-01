package icu.funkye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 陈健斌
 */

@SpringBootApplication
@EnableDiscoveryClient
public class NacosGetwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosGetwayApplication.class,args);
    }
}