package com.zay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-08-26 9:53
 */
@Component
@Slf4j
public class SwaggerPrintConfig implements ApplicationListener<WebServerInitializedEvent> {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        if (enableSwagger) {
            try {
                //获取IP
                String hostAddress = Inet4Address.getLocalHost().getHostAddress();
                //获取端口号
                int port = event.getWebServer().getPort();
                //获取应用名
                String applicationName = event.getApplicationContext().getApplicationName();
                log.info("Swagger接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/swagger-ui/index.html");
                log.info("Swagger接口文档地址: http://" + hostAddress + ":" + port + applicationName + "/doc.html");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
