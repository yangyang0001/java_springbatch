//package com.deepblue.springbatch.chapter_10.job_txt;
//
//import com.alibaba.fastjson.JSON;
//import com.deepblue.springbatch.common.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class MineFileDemoJob {
//
//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job mineFileJob) {
//        return args -> {
//            JobParameters params = new JobParametersBuilder()
//                    .addLong("run.id", System.currentTimeMillis()) // 用时间戳保证唯一
//                    .addString("filePath", "file/user.txt") // 用时间戳保证唯一
//                    .toJobParameters();
//            jobLauncher.run(mineFileJob, params);
//        };
//    }
//
//    @Bean
//    public Job mineFileJob(JobRepository jobRepository,
//                           PlatformTransactionManager transactionManager,
//                           FlatFileItemReader<User> fileItemReader) {
//        return new JobBuilder("mineFileJob", jobRepository)
//                .start(mineFileStep(jobRepository, transactionManager, fileItemReader))
//                .build();
//    }
//
//    @Bean
//    public Step mineFileStep(JobRepository jobRepository,
//                             PlatformTransactionManager transactionManager,
//                             FlatFileItemReader<User> fileItemReader) {
//        return new StepBuilder("mineFileStep", jobRepository)
//                .chunk(5, transactionManager)
//                .reader(fileItemReader)
//                .writer(items -> {
//                    log.info("本批次处理数量 --------------------------------------------------------------------------: {}", items.size());
//                    items.forEach(item -> log.info("--------------------------------------------------------------------------数据: {}", JSON.toJSONString(item)));
//                })
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<User> fileItemReader(@Value("#{jobParameters['filePath']}") String filePath) {
//        log.info("filePath --------------------------------------------------------------------------: {}", filePath);
//        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource(filePath));
//        reader.setLinesToSkip(1);
//
//        // 解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames("id", "name", "age", "gender", "del_flag"); // 要和 User 字段匹配
//        tokenizer.setDelimiter(",");
//
//        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(User.class);
//
//        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
//        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//
//        reader.setLineMapper(lineMapper);
//        return reader;
//    }
//}
