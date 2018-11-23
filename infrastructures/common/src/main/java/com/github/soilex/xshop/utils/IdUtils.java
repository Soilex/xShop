package com.github.soilex.xshop.utils;

import com.github.soilex.xshop.configurations.SnowFlakeConfig;
import com.github.soilex.xshop.mvc.SpringContextHolder;
import com.twitter.service.snowflake.IdWorker;

/**
 * 唯一id生成工具类
 */
public final class IdUtils {

    private static IdWorker idworkerInstance;

    private IdUtils() {

    }

    private static synchronized IdWorker getIdworker() {
        if (idworkerInstance == null) {
            SnowFlakeConfig snowflakeConfig = SpringContextHolder.getBean(SnowFlakeConfig.class);
            idworkerInstance = new IdWorker(snowflakeConfig.getWorkerId(), snowflakeConfig.getWorkerId(), snowflakeConfig.getSequence());
        }
        return idworkerInstance;
    }

    /**
     * 生成新的id
     * @return 新的id
     */
    public static long nextId() {
        return getIdworker().nextId();
    }
}
