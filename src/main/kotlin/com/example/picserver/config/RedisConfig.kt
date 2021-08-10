package com.example.picserver.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisSerializer(): RedisSerializer<Any> {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
        //不适用默认的dateTime进行序列化,使用JSR310的LocalDateTimeSerializer
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
        //重点,这是序列化LocalDateTIme和LocalDate的必要配置,由Jackson-data-JSR310实现
        objectMapper.registerModule(JavaTimeModule())
        //必须配置,有兴趣参考源码解读
        objectMapper.activateDefaultTyping(
            LaissezFaireSubTypeValidator.instance,
            ObjectMapper.DefaultTyping.NON_FINAL,
            JsonTypeInfo.As.PROPERTY
        )
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(objectMapper, null)
        return GenericJackson2JsonRedisSerializer(objectMapper)
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory,
        redisSerializer: RedisSerializer<Any?>
    ): RedisTemplate<Any, Any> {
        val template: RedisTemplate<Any, Any> = RedisTemplate()
        template.setConnectionFactory(redisConnectionFactory)
        template.setDefaultSerializer(redisSerializer)
        template.valueSerializer = redisSerializer
        template.hashValueSerializer = redisSerializer
        template.keySerializer = StringRedisSerializer.UTF_8
        template.hashKeySerializer = StringRedisSerializer.UTF_8
        template.afterPropertiesSet()
        return template
    }
}