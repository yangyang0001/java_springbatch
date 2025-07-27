package com.deepblue.springbatch.chapter_13.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepblue.springbatch.chapter_13.entity.MineRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MineRecordMapper extends BaseMapper<MineRecord> {

    List<MineRecord> selectListAfterId(@Param("taskId") String taskId, @Param("lastId") Long lastId, @Param("pageSize") int pageSize);

    List<MineRecord> selectList(MineRecord mineRecord);

    void updateBatch(@Param("records") List<MineRecord> records);

}
