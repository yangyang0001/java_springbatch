package com.deepblue.springbatch.chapter_14.config;

import com.deepblue.springbatch.chapter_13.entity.MineRecord;
import com.deepblue.springbatch.chapter_13.mapper.MineRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class MineRecordBatchConfig {

    @Resource
    private MineRecordBatchProcessor processor;

    @Resource
    private MineRecordBatchWriter writer;

    @Resource
    private JobRepository primaryJobRepository;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Autowired
    private MineRecordMapper recordMapper;

    public Job createJob(String jobName, String taskId) {
        return new JobBuilder(jobName, primaryJobRepository)
                .start(createStep(jobName + "_step", taskId))
                .build();
    }

    public Step createStep(String stepName, String taskId) {
        return new StepBuilder(stepName, primaryJobRepository)
                .<List<MineRecord>, List<MineRecord>>chunk(1, transactionManager)
                .reader(new MineRecordBatchReader(recordMapper, taskId)) // 直接 new，手动传 taskId
                .processor(processor)
                .writer(writer)
                .build();
    }

}
