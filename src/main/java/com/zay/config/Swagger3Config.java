package com.zay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @Author: ZhouAnYan
 * @Email: yan_zay@163.com
 * @Date: 2022-08-25 15:55
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {

    @Bean
    public Docket docket(Environment environment){
        Profiles profiles = Profiles.of("dev", "sit"); // 设置要显示swagger的环境
        boolean enableSwagger = environment.acceptsProfiles(profiles); // 判断当前是否处于该环境
        return new Docket(DocumentationType.OAS_30)
                //是否开启Swagger   根据配置文件环境判断
                .enable(enableSwagger)
                //分组名称
                .groupName("葫芦娃第一组")
                //文档信息配置
                .apiInfo(apiInfo())
                //配置扫描的接口
                .select()
                //配置扫描哪里的接口 添加swagger接口提取范围,修改成指向你的controller包
                .apis(RequestHandlerSelectors.basePackage("com.zay.controller"))
                //过滤请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("葫芦娃系统接口文档")
                .description("这是一个葫芦娃系统")
                .termsOfServiceUrl("https://www.baidu.com") // 组织链接
                .contact(new Contact("周安雁", "https://github.com/", "yan_zay@163.com"))
                .license("Apache 2.0 许可")// 许可
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")// 许可链接
                .version("666.66")// 版本
                .extensions(new ArrayList<>())// 拓展
                .build();
    }
}
