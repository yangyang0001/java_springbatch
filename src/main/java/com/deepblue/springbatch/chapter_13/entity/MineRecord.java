package com.deepblue.springbatch.chapter_13.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("mine_record")
public class MineRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String taskId;

    private String loginName;

    private String customerId;

    private String phone;

    private String remark;

    // 1: 初始化 2: 发送中 3: 成功  4: 失败
    private Integer status;

    private Date createTime;

    private Date updateTime;

}
