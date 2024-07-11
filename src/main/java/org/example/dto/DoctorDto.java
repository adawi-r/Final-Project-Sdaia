package org.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class DoctorDto {

    private int doctor_id;
    private String doctor_name;
    private String doctor_specialty;
//    private ArrayList<LinkDto> links = new ArrayList<>();

    public DoctorDto() {
    }

    public DoctorDto(int doctor_id, String doctor_name, String doctor_specialty) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_specialty = doctor_specialty;
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


    @XmlElementWrapper
    @XmlElement(name = "link")
//    public ArrayList<LinkDto> getLinks() {
//        return links;
//    }

    // XmlElement(name = "link")
    public void addLink(String url, String rel) {
        LinkDto link = new LinkDto();
        link.setLink(url);
        link.setRel(rel);
//        links.add(link);
    }

    public DoctorDto(ResultSet rs) throws SQLException {
        doctor_id = rs.getInt("doctor_id");
        doctor_name = rs.getString("doctor_name");
        doctor_specialty = rs.getString("doctor_specialty");
    }

    @Override
    public String toString() {
        return "DoctorDto{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", doctor_specialty='" + doctor_specialty + '\'' +
                '}';
    }
}
