package com.digitzh.railmind;

import com.digitzh.railmind.entity.Appointment;
import com.digitzh.railmind.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setEquipmentId("ZDJ9");
        appointment.setEquipmentName("转辙机");
        appointment.setFaultType("缺口");
        appointment.setDate("2025-04-14");
        appointment.setTime("11:45:50");
        appointment.setComment("转辙机检测杆的缺口值过大,使转辙机无法锁闭,从而导致道岔无法操作到位");
        Appointment appointmentDB = appointmentService.getOne(appointment);
        System.out.println(appointmentDB);
    }
    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setEquipmentId("RAIL707");
        appointment.setEquipmentName("钢轨");
        appointment.setFaultType("钢轨核伤");
        appointment.setDate("2025-05-15");
        appointment.setTime("12:30:00");
        appointment.setComment("钢轨存在白点、气泡和非金属夹杂物或严重偏析");
        appointmentService.save(appointment);
    }
    @Test
    void testRemoveById() {
        appointmentService.removeById(1L);
    }
}
