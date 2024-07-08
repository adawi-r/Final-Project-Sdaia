package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@XmlRootElement
public class ConsultationDtoAll {

    private int consultation_id;
    private int doctor_id;
    private int patient_id;
    private LocalDateTime consultation_request_time;
    private LocalDateTime consultation_time;
    private String consultation_status;
    private String consultation_diagnosis;
    private int consultation_rating;
    private ArrayList <LinkDto> links = new ArrayList<>();

    public ConsultationDtoAll() {
    }

    public ConsultationDtoAll(int consultation_id, int doctor_id, int patient_id, LocalDateTime consultation_request_time, LocalDateTime consultation_time, String consultation_status, String consultation_diagnosis, int consultation_rating) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.consultation_request_time = consultation_request_time;
        this.consultation_time = consultation_time;
        this.consultation_status = consultation_status;
        this.consultation_diagnosis = consultation_diagnosis;
        this.consultation_rating = consultation_rating;
    }

    //for rating
    public ConsultationDtoAll(int doctor_id, int consultation_rating) {
        this.doctor_id = doctor_id;
        this.consultation_rating = consultation_rating;
    }

    // for select one con


    public ConsultationDtoAll(int consultation_id, int doctor_id, LocalDateTime consultation_request_time, LocalDateTime consultation_time, String consultation_status, String consultation_diagnosis, int consultation_rating) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.consultation_request_time = consultation_request_time;
        this.consultation_time = consultation_time;
        this.consultation_status = consultation_status;
        this.consultation_diagnosis = consultation_diagnosis;
        this.consultation_rating = consultation_rating;
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
        this.consultation_time = LocalDateTime.now();
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

    public ConsultationDtoAll(ResultSet rs) throws SQLException {
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



    @Override
    public String toString() {
        return "ConsultationDto{" +
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
