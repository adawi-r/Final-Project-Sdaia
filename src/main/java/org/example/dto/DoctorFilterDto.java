package org.example.dto;

import jakarta.ws.rs.QueryParam;


public class DoctorFilterDto {

    @QueryParam("doctor_id") Integer doctor_id;
    @QueryParam("doctor_name") String doctor_name;
    @QueryParam("doctor_specialty") String doctor_specialty;
    @QueryParam("schedule_is_available") Boolean schedule_is_available;


    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_specialty() {
        return doctor_specialty;
    }

    public void setDoctor_specialty(String doctor_specialty) {
        this.doctor_specialty = doctor_specialty;
    }

    public Boolean getSchedule_is_available() {
        return schedule_is_available;
    }

    public void setSchedule_is_available(Boolean schedule_is_available) {
        this.schedule_is_available = schedule_is_available;
    }
}
