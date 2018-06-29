package com.stylefeng.guns.config.web;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * ehcache配置
 *
 * @author fengshuonan
 * @date 2017-05-20 23:11
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
 public RedisCacheManager cacheManager(RedisTemplate<String, Object>
                                                  redisTemplate) {
      RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
      redisCacheManager.setDefaultExpiration(30 * 60);
     return redisCacheManager;
        }

        @Bean
 public RedisTemplate<String, Object>
    redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
         template.afterPropertiesSet();
       return template;
      }
 }

