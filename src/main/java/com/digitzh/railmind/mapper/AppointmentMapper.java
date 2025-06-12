package com.digitzh.railmind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitzh.railmind.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
