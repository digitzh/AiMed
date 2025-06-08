package com.digitzh.aimed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitzh.aimed.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
