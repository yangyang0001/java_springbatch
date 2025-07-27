package com.deepblue.springbatch.chapter_13.mapper;

import com.deepblue.springbatch.chapter_13.entity.MineRecord;
import com.deepblue.springbatch.chapter_13.entity.MineTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MineTaskMapper {

    List<MineTask> selectList();

    int updateTask(@Param("taskId") String taskId);
}
