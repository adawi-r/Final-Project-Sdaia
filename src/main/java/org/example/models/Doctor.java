package org.example.models;

import jakarta.ws.rs.FormParam;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {

    @FormParam("doctor_id")
    private int doctor_id;

    @FormParam("doctor_name")
    private String doctor_name;

    @FormParam("doctor_specialty")
    private String doctor_specialty;

    @FormParam("doctor_email")
    private String doctor_email;

    @FormParam("doctor_password")
    private String doctor_password;

    @FormParam("doctor_phone")
    private String doctor_phone;


    public Doctor() {
    }

    public Doctor(int doctor_id, String doctor_name, String doctor_specialty, String doctor_email, String doctor_password, String doctor_phone) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_specialty = doctor_specialty;
        this.doctor_email = doctor_email;
        this.doctor_password = doctor_password;
        this.doctor_phone = doctor_phone;
    }
//
//    public Doctor(int doctor_id, String doctor_name, String doctor_specialty) {
//        this.doctor_id = doctor_id;
//        this.doctor_name = doctor_name;
//        this.doctor_specialty = doctor_specialty;
//    }

    public Doctor(ResultSet rs) throws SQLException {

        doctor_id = rs.getInt("doctor_id");
        doctor_name = rs.getString("doctor_name");
        doctor_specialty = rs.getString("doctor_specialty");
        doctor_email = rs.getString("doctor_email");
        doctor_password = rs.getString("doctor_password");
        doctor_phone = rs.getString("doctor_phone");
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
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

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public String getDoctor_phone() {
        return doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        this.doctor_phone = doctor_phone;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", doctor_specialty='" + doctor_specialty + '\'' +
                ", doctor_email='" + doctor_email + '\'' +
                ", doctor_password='" + doctor_password + '\'' +
                ", doctor_phone='" + doctor_phone + '\'' +
                '}';
    }
}
