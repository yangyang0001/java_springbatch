package com.deepblue.springbatch.chapter_11;

import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;

import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;

@Configuration
public class BatchRepositoryConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ExecutionContextSerializer executionContextSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }

    @Bean
    @Primary
    public JobRepository jobRepository(PlatformTransactionManager transactionManager,
                                       ExecutionContextSerializer executionContextSerializer) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);

        // ✅ 设置 JSON 序列化
        factory.setSerializer(executionContextSerializer);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
