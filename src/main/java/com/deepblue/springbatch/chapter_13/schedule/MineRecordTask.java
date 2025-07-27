//package com.deepblue.springbatch.chapter_13.schedule;
//
//import com.deepblue.springbatch.chapter_13.entity.MineTask;
//import com.deepblue.springbatch.chapter_13.mapper.MineTaskMapper;
//import jakarta.annotation.Resource;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 测试定时任务
// */
//@Component
//public class MineRecordTask extends CommonRecordTask{
//
//    @Resource
//    private JobLauncher jobLauncher;
//
//    @Resource
//    private MineTaskMapper taskMapper;
//
//
//    @Scheduled(cron = "0/30 * * * * ?")
//    public void doTask() {
//        List<MineTask> tasks = taskMapper.selectList();
//        tasks.forEach(task -> {
//            String taskId = task.getTaskId();
//            Job job = buildTaskJob(taskId);
//            JobParameters params = new JobParametersBuilder()
//                    .addString("taskId", taskId)
//                    .toJobParameters();
//            try {
//                jobLauncher.run(job, params);
//            } catch (JobExecutionAlreadyRunningException e) {
//                throw new RuntimeException(e);
//            } catch (JobRestartException e) {
//                throw new RuntimeException(e);
//            } catch (JobInstanceAlreadyCompleteException e) {
//                throw new RuntimeException(e);
//            } catch (JobParametersInvalidException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//    }
//
//
//
//}
