package com.github.soilex.xshop.configurations;

import com.github.soilex.xshop.exceptions.ExceptionLevel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 异常配置
 */
@Data
@Configuration
@ConfigurationProperties("spring.exception")
public class ExceptionConfig {

    private String module = "app";

    private ExceptionLevel logLevel = ExceptionLevel.Service;

}
