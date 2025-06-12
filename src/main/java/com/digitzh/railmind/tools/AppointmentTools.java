package com.digitzh.railmind.tools;

import com.digitzh.railmind.entity.Appointment;
import com.digitzh.railmind.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {
    @Autowired
    private AppointmentService appointmentService;

    @Tool(name="添加故障记录", value = "根据参数，先执行工具方法getOne查询是否已记录、向用户回答，并让用户确认所有记录信息，用户确认后再进行添加。")
    public String bookAppointment(Appointment appointment){
        //查找数据库中是否包含对应的记录
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB == null){
            appointment.setId(null);//防止大模型幻觉设置了id
            if(appointmentService.save(appointment)){
                return "添加成功，并返回添加详情";
            }else{
                return "添加失败";
            }
        }
        return "该记录已存在于数据库中";
    }

    @Tool(name="删除故障记录", value = "根据参数，查询故障是否存在，如果存在则删除记录并返回删除成功，否则返回删除失败")
    public String cancelAppointment(Appointment appointment){
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB != null){
            //删除故障记录
            if(appointmentService.removeById(appointmentDB.getId())){
                return "删除记录成功";
            }else{
                return "删除记录失败";
            }
        }
        //取消失败
        return "记录不存在，请检查记录信息";
    }

    @Tool(name = "查询记录", value="根据设备名称、ID、时间、日期、故障类型等查询记录是否存在，并返回给用户")
    public boolean queryDepartment(
            @P(value = "设备名称") String name,
            @P(value = "设备ID") String uniqueId,
            @P(value = "日期 YYYY-mm-dd", required = false) String date,
            @P(value = "时间 HH:mm:ss", required = false) String time,
            @P(value = "故障类型", required = false) String faultType
    ) {
        System.out.println("查询记录");
        System.out.println("设备名称：" + name);
        System.out.println("设备ID：" + uniqueId);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("故障类型：" + faultType);

        return true;
    }
}
