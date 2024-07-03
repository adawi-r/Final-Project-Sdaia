package org.example.models;

import jakarta.ws.rs.FormParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MedicalReport {

    @FormParam("medical_report_id")
    private int medical_report_id;

    @FormParam("patient_id")
    private int patient_id;

    @FormParam("medical_report_details")
    private String medical_report_details;

    @FormParam("medical_report_report_date")
    private LocalDateTime medical_report_report_date;

    public MedicalReport() {
    }

    public MedicalReport(int medical_report_id, int patient_id, String medical_report_details, LocalDateTime medical_report_report_date) {
        this.medical_report_id = medical_report_id;
        this.patient_id = patient_id;
        this.medical_report_details = medical_report_details;
        this.medical_report_report_date = medical_report_report_date;
    }


    public MedicalReport(ResultSet rs) throws SQLException {

        medical_report_id = rs.getInt("medical_report_id");
        patient_id = rs.getInt("patient_id");
        medical_report_details = rs.getString("medical_report_details");
        medical_report_report_date = LocalDateTime.parse(rs.getString("medical_report_report_date"));
    }

    public int getMedical_report_id() {
        return medical_report_id;
    }

    public void setMedical_report_id(int medical_report_id) {
        this.medical_report_id = medical_report_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedical_report_details() {
        return medical_report_details;
    }

    public void setMedical_report_details(String medical_report_details) {
        this.medical_report_details = medical_report_details;
    }

    public LocalDateTime getMedical_report_report_date() {
        return medical_report_report_date;
    }

    public void setMedical_report_report_date(LocalDateTime medical_report_report_date) {
        this.medical_report_report_date = medical_report_report_date;
    }

    @Override
    public String toString() {
        return "MedicalReport{" +
                "medical_report_id=" + medical_report_id +
                ", patient_id=" + patient_id +
                ", medical_report_details='" + medical_report_details + '\'' +
                ", medical_report_report_date=" + medical_report_report_date +
                '}';
    }
}
