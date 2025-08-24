package com.deepblue.springbatch.chapter_14.config;

import com.deepblue.springbatch.chapter_13.entity.MineRecord;
import com.deepblue.springbatch.chapter_13.mapper.MineRecordMapper;
import org.springframework.batch.item.*;

import java.util.List;


public class MineRecordBatchReader implements ItemStreamReader<List<MineRecord>> {

    private MineRecordMapper recordMapper;

    private ExecutionContext executionContext;

    private int pageSize = 3;

    private String taskId;

    private Long lastId = 0L;

    public MineRecordBatchReader(MineRecordMapper recordMapper, String taskId) {
        this.recordMapper = recordMapper;
        this.taskId = taskId;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("MineRecordBatchReader invoke 1 open");
        this.executionContext = executionContext;

        if(this.executionContext.containsKey("taskId")) {
            this.taskId = this.executionContext.getString("taskId");
        } else {
            this.executionContext.put("taskId", this.taskId);
        }

        if(this.executionContext.containsKey("lastId")) {
            this.lastId = this.executionContext.getLong("lastId");
        } else {
            this.lastId = 0L;
            this.executionContext.put("lastId", this.lastId);
        }

    }

    @Override
    public List<MineRecord> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("MineRecordBatchReader invoke 2 read, taskId = " + this.taskId + ", lastId = " + this.lastId);
        List<MineRecord> records = recordMapper.selectListAfterId(this.taskId, this.lastId, this.pageSize);

        if (!records.isEmpty()) {
            this.lastId = records.get(records.size() - 1).getId();
            return records;
        } else {
            return null;
        }

    }


    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("MineRecordBatchReader invoke 3 update");
        this.executionContext.put("lastId", this.lastId);
        this.executionContext.put("taskId", this.taskId);
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("MineRecordBatchReader invoke 4 close");
        ItemStreamReader.super.close();
    }
}
