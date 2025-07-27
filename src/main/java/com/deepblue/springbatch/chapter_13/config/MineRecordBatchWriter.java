//package com.deepblue.springbatch.chapter_13.config;
//
//import com.alibaba.fastjson.JSON;
//import com.deepblue.springbatch.chapter_13.entity.MineRecord;
//import com.deepblue.springbatch.chapter_13.mapper.MineRecordMapper;
//import com.deepblue.springbatch.chapter_13.mapper.MineTaskMapper;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MineRecordBatchWriter implements ItemWriter<List<MineRecord>> {
//
//    @Autowired
//    private MineRecordMapper recordMapper;
//
//    @Autowired
//    private MineTaskMapper taskMapper;
//
//    @Override
//    public void write(Chunk<? extends List<MineRecord>> chunk) throws Exception {
//
//        List<? extends List<MineRecord>> items = chunk.getItems();
//        List<MineRecord> records = items.stream().flatMap(List::stream).toList();
//        Thread.sleep(5000L);
//
//        System.out.println("MineRecordBatchWriter ------------------------------ " +
//                "records.size: " + records.size() +
//                ", records:" + JSON.toJSONString(records));
//
//        recordMapper.updateBatch(records);
//
//        taskMapper.updateTask(records.getFirst().getTaskId());
//
//    }
//}
