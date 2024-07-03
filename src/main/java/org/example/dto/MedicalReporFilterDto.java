package org.example.dto;
import jakarta.ws.rs.QueryParam;
public class MedicalReporFilterDto {

    @QueryParam("patient_id") Integer patient_id;
    @QueryParam("medical_report_details") String medical_report_details;

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedical_report_details() {
        return medical_report_details;
    }

    public void setMedical_report_details(String medical_report_details) {
        this.medical_report_details = medical_report_details;
    }
}
