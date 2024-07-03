package org.example.dto;

import jakarta.ws.rs.QueryParam;

public class ScheduleFilterDto {

    @QueryParam("doctor_id") Integer doctor_id;
    @QueryParam("schedule_is_available") Boolean schedule_is_available;

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Boolean getSchedule_is_available() {
        return schedule_is_available;
    }

    public void setSchedule_is_available(Boolean schedule_is_available) {
        this.schedule_is_available = schedule_is_available;
    }
}
