package org.example.models;

import jakarta.ws.rs.FormParam;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {

    @FormParam("patient_id")
    private int patient_id;

    @FormParam("patient_name")
    private String patient_name;

    @FormParam("patient_email")
    private String patient_email;

    @FormParam("patient_password")
    private String patient_password;

    @FormParam("patient_phone")
    private String patient_phone;

    @FormParam("patient_birth_date")
    private String patient_birth_date;

    public Patient() {
    }

    public Patient(int patient_id, String patient_name, String patient_email, String patient_password, String patient_phone, String patient_birth_date) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_password = patient_password;
        this.patient_phone = patient_phone;
        this.patient_birth_date = patient_birth_date;
    }

    // check it
    public Patient(ResultSet rs) throws SQLException {

        patient_id = rs.getInt("patient_id");
        patient_name = rs.getString("patient_name");
        patient_email = rs.getString("patient_email");
        patient_password = rs.getString("patient_password");
        patient_birth_date = rs.getString("patient_birth_date");
        patient_phone = rs.getString("patient_phone");
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public String getPatient_birth_date() {
        return patient_birth_date;
    }

    public void setPatient_birth_date(String patient_birth_date) {
        this.patient_birth_date = patient_birth_date;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_email='" + patient_email + '\'' +
                ", patient_password='" + patient_password + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", patient_birth_date=" + patient_birth_date +
                '}';
    }
}
