package org.example.dto;

import jakarta.ws.rs.FormParam;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@XmlRootElement
public class MedicalReportDto {


    private int medical_report_id;
    private int patient_id;
    private String medical_report_details;
    private LocalDateTime medical_report_report_date;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public MedicalReportDto() {
    }

    public MedicalReportDto(int medical_report_id, int patient_id, String medical_report_details, LocalDateTime medical_report_report_date) {
        this.medical_report_id = medical_report_id;
        this.patient_id = patient_id;
        this.medical_report_details = medical_report_details;
        this.medical_report_report_date = medical_report_report_date;
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

    public MedicalReportDto(ResultSet rs) throws SQLException {
        medical_report_id = rs.getInt("medical_report_id");
        patient_id = rs.getInt("patient_id");
        medical_report_details = rs.getString("medical_report_details");
        medical_report_report_date = LocalDateTime.parse(rs.getString("medical_report_report_date"));
    }


    @Override
    public String toString() {
        return "MedicalReportDto{" +
                "medical_report_id=" + medical_report_id +
                ", patient_id=" + patient_id +
                ", medical_report_details='" + medical_report_details + '\'' +
                ", medical_report_report_date=" + medical_report_report_date +
                '}';
    }
}
