package com.deepblue.springbatch.chapter_13.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("mine_task")
public class MineTask {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String taskId;

    private String taskName;

    // 1: 未执行 2: 已执行
    private int status;

    private Date createTime;

    private Date updateTime;

}
