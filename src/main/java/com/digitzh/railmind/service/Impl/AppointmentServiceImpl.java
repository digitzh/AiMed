package com.digitzh.railmind.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitzh.railmind.entity.Appointment;
import com.digitzh.railmind.mapper.AppointmentMapper;
import com.digitzh.railmind.service.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment>
        implements AppointmentService {
    /**
     * 查询记录是否存在
     * @param appointment
     * @return
     */
    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getEquipmentId, appointment.getEquipmentId());
        queryWrapper.eq(Appointment::getEquipmentName, appointment.getEquipmentName());
        queryWrapper.eq(Appointment::getDate, appointment.getDate());
        queryWrapper.eq(Appointment::getTime, appointment.getTime());
        queryWrapper.eq(Appointment::getFaultType, appointment.getFaultType());
        queryWrapper.eq(Appointment::getComment, appointment.getComment());

        Appointment appointmentDB = baseMapper.selectOne(queryWrapper);
        return appointmentDB;
    }
}