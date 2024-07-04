package org.example.dto;

import jakarta.ws.rs.QueryParam;

import java.time.LocalDateTime;

public class ConsultationFilterDto {

    @QueryParam("consultation_diagnosis") String consultation_diagnosis;
    @QueryParam("consultation_rating") Integer consultation_rating;
    @QueryParam("pendingReq") LocalDateTime pendingRequest;
    @QueryParam("consultation_status") String consultation_status;
    @QueryParam("doctor_id") Integer doctor_id;
    @QueryParam("patient_id") Integer patient_id;


    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getConsultation_status() {
        return consultation_status;
    }

    public void setConsultation_status(String consultation_status) {
        this.consultation_status = consultation_status;
    }

    public LocalDateTime getPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(LocalDateTime pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public Integer getConsultation_rating() {
        return consultation_rating;
    }

    public void setConsultation_rating(Integer consultation_rating) {
        this.consultation_rating = consultation_rating;
    }

    public String getConsultation_diagnosis() {
        return consultation_diagnosis;
    }

    public void setConsultation_diagnosis(String consultation_diagnosis) {
        this.consultation_diagnosis = consultation_diagnosis;
    }

}
