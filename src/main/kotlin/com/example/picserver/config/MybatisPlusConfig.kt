package com.example.picserver.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer
import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.apache.ibatis.reflection.MetaObject
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.*


@Configuration
class MybatisPlusConfig {
    /**
     * 分页
     * @return
     */
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(DbType.MYSQL))
        return interceptor
    }

    /**
     * 自动注入createTime和updateTime
     *
     * @return
     */
    @Bean
    fun setMyBatisPlusMetaObjectHandler(): MetaObjectHandler {
        return object : MetaObjectHandler {
            override fun insertFill(metaObject: MetaObject) {
                this.strictInsertFill(
                    metaObject,
                    "createTime",
                    LocalDateTime::class.java,
                    LocalDateTime.now()
                ) // 起始版本 3.3.0(推荐使用)
                this.strictInsertFill(
                    metaObject,
                    "updateTime",
                    LocalDateTime::class.java,
                    LocalDateTime.now()
                )
            }

            override fun updateFill(metaObject: MetaObject) {
                this.strictUpdateFill(
                    metaObject,
                    "updateTime",
                    LocalDateTime::class.java,
                    LocalDateTime.now()
                )
            }

//            /**
//             * 当被填充的字段有值时 不 忽略填充（默认有值会忽略）
//             * @param metaObject
//             * @param fieldName
//             * @param fieldVal
//             * @return
//             */
//            override fun strictFillStrategy(
//                metaObject: MetaObject,
//                fieldName: String,
//                fieldVal: Supplier<*>
//            ): MetaObjectHandler {
//                val obj = fieldVal.get()
//                if (Objects.nonNull(obj)) {
//                    metaObject.setValue(fieldName, obj)
//                }
//                return this
//            }
        }
    }
}