package com.digitzh.railmind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    // 主键生成策略
    @TableId(type = IdType.AUTO)
    private Long id;
    private String equipmentId;
    private String equipmentName;
    private String date;
    private String time;
    private String faultType;
    private String comment;
}
