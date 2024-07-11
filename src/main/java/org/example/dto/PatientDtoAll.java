package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class PatientDtoAll {

    private int patient_id;
    private String patient_name;
    private String patient_email;
    private String patient_password;
    private String patient_phone;
    private String patient_birth_date;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public PatientDtoAll() {
    }

    public PatientDtoAll(int patient_id, String patient_name, String patient_email, String patient_password, String patient_phone, String patient_birth_date) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_email = patient_email;
        this.patient_password = patient_password;
        this.patient_phone = patient_phone;
        this.patient_birth_date = patient_birth_date;
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

    @XmlElementWrapper
    @XmlElement(name = "link")
    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    // XmlElement(name = "link")
    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public PatientDtoAll(ResultSet rs) throws SQLException {
        patient_id = rs.getInt("patient_id");
        patient_name = rs.getString("patient_name");
        patient_email = rs.getString("patient_email");
        patient_password = rs.getString("patient_password");
        patient_birth_date = rs.getString("patient_birth_date");
        patient_phone = rs.getString("patient_phone");
}

    @Override
    public String toString() {
        return "PatientDto{" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_email='" + patient_email + '\'' +
                ", patient_password='" + patient_password + '\'' +
                ", patient_phone='" + patient_phone + '\'' +
                ", patient_birth_date=" + patient_birth_date +
                '}';
    }
}
