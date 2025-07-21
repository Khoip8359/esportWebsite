package com.example.Esport_Website.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }

    @Bean
    public HealthIndicator databaseHealthIndicator(@Autowired DataSource dataSource) {
        return new HealthIndicator() {
            @Override
            public Health health() {
                try {
                    if (dataSource instanceof HikariDataSource) {
                        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                        return Health.up()
                                .withDetail("poolName", hikariDataSource.getPoolName())
                                .withDetail("maximumPoolSize", hikariDataSource.getMaximumPoolSize())
                                .withDetail("minimumIdle", hikariDataSource.getMinimumIdle())
                                .withDetail("activeConnections", hikariDataSource.getHikariPoolMXBean().getActiveConnections())
                                .withDetail("idleConnections", hikariDataSource.getHikariPoolMXBean().getIdleConnections())
                                .withDetail("totalConnections", hikariDataSource.getHikariPoolMXBean().getTotalConnections())
                                .build();
                    }
                    return Health.up().withDetail("dataSource", dataSource.getClass().getSimpleName()).build();
                } catch (Exception e) {
                    return Health.down().withException(e).build();
                }
            }
        };
    }
} 