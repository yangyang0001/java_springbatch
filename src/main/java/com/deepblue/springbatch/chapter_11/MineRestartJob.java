//package com.deepblue.springbatch.chapter_11;
//
//import com.alibaba.fastjson.JSON;
//import com.deepblue.springbatch.common.entity.User;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class MineRestartJob {
//
//    @Resource
//    private RestartReader restartReader;
//
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job retartJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addString("filePath", "file/restart.txt") // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(retartJob, params);
//        };
//    }
//
//    @Bean
//    public Job retartJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("retartJob", jobRepository)
//                .start(restartStep(jobRepository, transactionManager))
//                .build();
//    }
//
//    @Bean
//    public Step restartStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("restartStep", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(restartReader)
//                .processor(item -> {
//                    User user = (User) item;
//                    if (user.getName() == null || user.getName().isBlank()) {
//                        throw new RuntimeException("name is blank");
//                    }
//                    user.setName(user.getName().toUpperCase());
//                    return user;
//                })
////                // 添加容错机制
////                .faultTolerant()
////                // 跳过 RuntimeException
////                .skip(RuntimeException.class)
////                // 允许最多跳过 100 条
////                .skipLimit(100)
//                .writer(items -> {
//                    log.info("本批次处理数量 --------------------------------------------------------------------------: {}", items.size());
//                    items.forEach(item -> log.info("--------------------------------------------------------------------------数据: {}", JSON.toJSONString(item)));
//                })
//                .build();
//    }
//}
