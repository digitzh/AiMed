package com.digitzh.railmind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitzh.railmind.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
