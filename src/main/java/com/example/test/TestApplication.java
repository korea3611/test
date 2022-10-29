package com.example.test;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return Redisson.create(config);

    }

}
