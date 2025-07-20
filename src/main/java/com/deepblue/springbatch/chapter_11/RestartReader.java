package com.deepblue.springbatch.chapter_11;

import com.deepblue.springbatch.common.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class RestartReader implements ItemStreamReader<User> {

    private FlatFileItemReader<User> reader;

    private Long currentLine = 2L;
    private boolean restart = false;
    private ExecutionContext executionContext;

    public RestartReader() {
        // 构造函数中不初始化 reader
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.executionContext = executionContext;

        if (executionContext.containsKey("currentLine")) {
            this.currentLine = executionContext.getLong("currentLine");
            this.restart = true;
        } else {
            this.currentLine = 2L;
            executionContext.put("currentLine", currentLine);
        }

        log.info("RestartReader open(), currentLine = {}, restart = {}", currentLine, restart);

        // 初始化 reader
        reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("file/restart.txt"));
        reader.setLinesToSkip(currentLine.intValue() - 1); // skip 已处理行

        // 设置解析器
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "age", "gender", "del_flag");
        tokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        reader.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("currentLine", currentLine);
        log.info("RestartReader update(), currentLine = {}", currentLine);
    }

    @Override
    public void close() throws ItemStreamException {
        log.info("RestartReader close(), currentLine = {}", currentLine);
        if (reader != null) {
            reader.close();
        }
    }

    @Override
    public User read() throws Exception {
        User user = reader.read();
        if (user != null) {
            currentLine++;
            if (user.getName() == null || user.getName().isBlank()) {
                throw new RuntimeException("User name is blank");
            }
        }
        return user;
    }
}
