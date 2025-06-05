package com.digitzh.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitzh.ai.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
