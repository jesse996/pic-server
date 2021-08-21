package com.example.picserver.config

import cn.hutool.core.io.FileUtil
import cn.hutool.json.JSONUtil
import com.alipay.easysdk.factory.Factory
import com.alipay.easysdk.kernel.Config
import io.swagger.annotations.ApiModelProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @ClassName : AlipayConfig
 * @Description : AlipayConfig
 * @Date: 2020-09-22 14:16
 */
@Component
@ConfigurationProperties(prefix = "alipay")
class AlipayConfig {
    @ApiModelProperty("应用ID")
    var appId: String? = null

    @ApiModelProperty("应用私钥")
    var merchantPrivateKey: String? = null

    @ApiModelProperty("支付宝公钥")
    var alipayPublicKey: String? = null

    @ApiModelProperty("支付回调地址")
    var notifyUrl: String? = null

    @ApiModelProperty("网关")
    var gatewayHost: String? = null

    /**
     * 初始化工厂配置参数
     */
    @PostConstruct
    fun initOptions() {
        val config = Config()
        config.appId = appId
        config.merchantPrivateKey = merchantPrivateKey
            ?: FileUtil.readUtf8String("C:\\Users\\jesse\\Documents\\支付宝开放平台开发助手\\RSA密钥\\应用私钥2048.txt")
        config.alipayPublicKey = alipayPublicKey;
        config.notifyUrl = notifyUrl
        config.signType = "RSA2"
        config.ignoreSSL = true
        config.gatewayHost = gatewayHost
        config.protocol = "https"

        println(JSONUtil.toJsonPrettyStr(config))
        Factory.setOptions(config)
    }
}