//package com.deepblue.springbatch.chapter_13.schedule;
//
//import com.deepblue.springbatch.chapter_13.config.MineRecordBatchProcessor;
//import com.deepblue.springbatch.chapter_13.config.MineRecordBatchReader;
//import com.deepblue.springbatch.chapter_13.config.MineRecordBatchWriter;
//import com.deepblue.springbatch.chapter_13.entity.MineRecord;
//import com.deepblue.springbatch.chapter_13.mapper.MineRecordMapper;
//import jakarta.annotation.Resource;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.List;
//
//@Component
//public abstract class CommonRecordTask {
//
//    @Resource
//    private JobRepository primaryJobRepository;
//
//    @Resource
//    private PlatformTransactionManager transactionManager;
//
//    @Resource
//    private MineRecordBatchProcessor processor;
//
//    @Resource
//    private MineRecordBatchWriter writer;
//
//    @Resource
//    private MineRecordMapper recordMapper;
//
//    public Job buildTaskJob(String taskId) {
//        return new JobBuilder("TaskJob-" + taskId, primaryJobRepository)
//                .start(mineRecordStep(taskId))
//                .build();
//    }
//
//    public Step mineRecordStep(String taskId) {
//        return new StepBuilder("mineRecordStep", primaryJobRepository)
//                .<List<MineRecord>, List<MineRecord>>chunk(1, transactionManager)
//                .reader(reader(taskId))
//                .processor(processor)
//                .writer(writer)
//                .build();
//    }
//
//    public MineRecordBatchReader reader(String taskId) {
//        System.out.println("创建 Reader Bean，taskId = " + taskId);
//        return new MineRecordBatchReader(recordMapper, taskId);
//    }
//}
