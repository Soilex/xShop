package com.github.soilex.xshop.data.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
@ConditionalOnClass(MongoClient.class)
public class MongoAutoConfiguration {

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory factory) {
        return new MongoTransactionManager(factory);
    }

    @Bean
    public MongoClientOptions mongoClientOptions(MongoSettingsProperties properties) {
        if (properties == null) {
            return new MongoClientOptions.Builder().build();
        }

        return new MongoClientOptions.Builder()
                .minConnectionsPerHost(properties.getMinConnectionsPerHost())
                .connectionsPerHost(properties.getMaxConnectionsPerHost())
                .threadsAllowedToBlockForConnectionMultiplier(properties.getThreadsAllowedToBlockForConnectionMultiplier())
                .maxWaitTime(properties.getMaxWaitTime())
                .maxConnectionIdleTime(properties.getMaxConnectionIdleTime())
                .maxConnectionLifeTime(properties.getMaxConnectionLifeTime())
                .connectTimeout(properties.getConnectTimeout())
                .socketTimeout(properties.getSocketTimeout())
                .sslEnabled(properties.getSslEnabled())
                .sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed())
                .alwaysUseMBeans(properties.getAlwaysUseMBeans())
                .heartbeatFrequency(properties.getHeartbeatFrequency())
                .minHeartbeatFrequency(properties.getMinHeartbeatFrequency())
                .heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout())
                .heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout())
                .localThreshold(properties.getLocalThreshold())
                .retryWrites(properties.isRetryWrites())
                .serverSelectionTimeout(properties.getServerSelectionTimeout())
                .writeConcern(properties.getWriteConcern())
                .readConcern(properties.getReadConcern())
                .readPreference(properties.getReadPreference())
                .build();
    }
}
