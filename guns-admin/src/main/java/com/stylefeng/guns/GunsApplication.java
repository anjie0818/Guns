package com.stylefeng.guns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
public class GunsApplication {
    @Autowired
    RedisTemplate redisTemplate;
    private final static Logger logger = LoggerFactory.getLogger(GunsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GunsApplication.class, args);
        logger.info("GunsApplication is success!");
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                BoundValueOperations<String, Object> test = redisTemplate.boundValueOps("test");
                test.set("test value");
                Object o = test.get();
                System.out.println(o);
            }

        };
    }
}