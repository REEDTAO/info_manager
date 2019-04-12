package com.reed.info_manager.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer(){
//        return new ConfigurationCustomizer() {
//            @Override
//            public void customize(Configuration configuration) {
//                //开启驼峰命名法自动映射
//                configuration.setMapUnderscoreToCamelCase(true);
//            }
//        };
//    }
}
