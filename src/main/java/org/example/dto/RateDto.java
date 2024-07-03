package org.example.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateDto {
    private int doctor_id;

    public RateDto() {
    }

    
    public RateDto(int doctor_id) {
        this.doctor_id = doctor_id;
    }


    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public RateDto(ResultSet rs) throws SQLException {
        doctor_id = rs.getInt("doctor_id");
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "doctor_id=" + doctor_id +
                '}';
    }
}
