package com.github.soilex.xshop.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Twitter SnowFlake 分布式id生成算法配置
 */
@Data
@Configuration
@ConfigurationProperties("snowflake")
public class SnowFlakeConfig {
    /**
     * 5位工作机器id，取值范围：0-31
     */
    private int workerId = 0;

    /**
     * 5位数据中心id，取值范围：0-31
     */
    private int datacenterId = 0;

    /**
     * 12位序列号，用来记录同毫秒内产生的不同id。取值范围：0-4095
     */
    private int sequence = 0;
}
