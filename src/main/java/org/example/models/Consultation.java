package org.example.models;

import jakarta.ws.rs.FormParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consultation {

    @FormParam("consultation_id")
    private int consultation_id;

    @FormParam("doctor_id")
    private int doctor_id;

    @FormParam("patient_id")
    private int patient_id;

    @FormParam("consultation_request_time")
    private LocalDateTime consultation_request_time;

    @FormParam("consultation_time")
    private LocalDateTime consultation_time;

    @FormParam("consultation_status")
    private String consultation_status;

    @FormParam("consultation_diagnosis")
    private String consultation_diagnosis;

    @FormParam("consultation_rating")
    private int consultation_rating;

    public Consultation() {
    }

    public Consultation(int consultation_id, int doctor_id, int patient_id, LocalDateTime consultation_request_time, LocalDateTime consultation_time, String consultation_status, String consultation_diagnosis, int consultation_rating) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.consultation_request_time = consultation_request_time;
        this.consultation_time = consultation_time;
        this.consultation_status = consultation_status;
        this.consultation_diagnosis = consultation_diagnosis;
        this.consultation_rating = consultation_rating;
    }

    public Consultation(ResultSet rs) throws SQLException {
        consultation_id = rs.getInt("consultation_id");
        doctor_id = rs.getInt("doctor_id");
        patient_id = rs.getInt("patient_id");
        consultation_request_time = LocalDateTime.parse(rs.getString("consultation_request_time"));
        consultation_time = LocalDateTime.parse(rs.getString("consultation_time"));

//        if(rs.getString("consultation_time").equals("")){
//            consultation_time = null;
//        }else {
//            consultation_time = LocalDateTime.parse(rs.getString("consultation_time"));
//        }

        consultation_status = rs.getString("consultation_status");
        consultation_diagnosis = rs.getString("consultation_diagnosis");
        consultation_rating = rs.getInt("consultation_rating");
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDateTime getConsultation_request_time() {
        return consultation_request_time;
    }

    public void setConsultation_request_time(LocalDateTime consultation_request_time) {
        this.consultation_request_time = consultation_request_time;
    }

    public LocalDateTime getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(LocalDateTime consultation_time) {
        this.consultation_time = consultation_time;
    }

    public String getConsultation_status() {
        return consultation_status;
    }

    public void setConsultation_status(String consultation_status) {
        this.consultation_status = consultation_status;
    }

    public String getConsultation_diagnosis() {
        return consultation_diagnosis;
    }

    public void setConsultation_diagnosis(String consultation_diagnosis) {
        this.consultation_diagnosis = consultation_diagnosis;
    }

    public int getConsultation_rating() {
        return consultation_rating;
    }

    public void setConsultation_rating(int consultation_rating) {
        this.consultation_rating = consultation_rating;
    }

    @Override
    public String
    toString() {
        return "Consultation{" +
                "consultation_id=" + consultation_id +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                ", consultation_request_time=" + consultation_request_time +
                ", consultation_time=" + consultation_time +
                ", consultation_status='" + consultation_status + '\'' +
                ", consultation_diagnosis='" + consultation_diagnosis + '\'' +
                ", consultation_rating=" + consultation_rating +
                '}';
    }
}
