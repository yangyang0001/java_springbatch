//package com.deepblue.springbatch.chapter_13.config;
//
//import com.deepblue.springbatch.chapter_13.entity.MineRecord;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MineRecordBatchProcessor implements ItemProcessor<List<MineRecord>, List<MineRecord>> {
//
//    @Override
//    public List<MineRecord> process(List<MineRecord> items) throws Exception {
//        for (MineRecord record : items) {
//            record.setStatus(2);
//        }
//        return items;
//    }
//}
