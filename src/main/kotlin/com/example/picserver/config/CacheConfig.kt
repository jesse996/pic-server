package com.example.picserver.config

import cn.hutool.json.JSONUtil
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.util.StringUtils
import java.lang.StringBuilder
import java.lang.reflect.Method
import java.time.Duration

@Configuration
@EnableCaching
class CacheConfig : CachingConfigurerSupport() {
    @Bean
    fun cacheManager(
        @Qualifier("redisConnectionFactory") factory: RedisConnectionFactory?,
        redisSerializer: RedisSerializer<Any>
    ): CacheManager {
        return RedisCacheManager.builder(factory!!)
            .cacheDefaults(getRedisCacheConfigurationWithTtl(redisSerializer))
            .build()
    }

    private fun getRedisCacheConfigurationWithTtl(redisSerializer: RedisSerializer<Any>): RedisCacheConfiguration =
        RedisCacheConfiguration.defaultCacheConfig().prefixCacheNameWith("pic:")
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
            .entryTtl(Duration.ofMinutes(60))


    override fun keyGenerator(): KeyGenerator? {
        // 当没有指定缓存的 key时来根据类名、方法名和方法参数来生成key
        return KeyGenerator { target: Any, method: Method, params: Array<Any?> ->
            val sb = StringBuilder()
            sb.append(target.javaClass.name)
                .append(':')
                .append(method.name)
            if (params.isNotEmpty()) {
                sb.append('[')
                for (obj in params) {
                    if (obj != null) {
                        sb.append(JSONUtil.toJsonStr(obj))
                    }
                }
                sb.append(']')
            }
            sb.toString()
        }
    }
}