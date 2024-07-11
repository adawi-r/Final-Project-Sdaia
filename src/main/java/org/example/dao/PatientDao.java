package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.PatientDto;
import org.example.dto.PatientDtoAll;
import org.example.models.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientDao {

//    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Final_ProjectV1\\FinalDataV4.db";

    //Requirements
    private static final String SELECT_ALL_PATIEN = "select * from PATIENTS";
    private static final String SELECT_ONE_PATIEN = "select * from PATIENTS where patient_id = ?";

    private static final String INSERT_PATIENT_REGISTER = "insert into PATIENTS ( patient_name, patient_email, patient_password, patient_birth_date, patient_phone) values (?, ?, ?, ?, ?)";

    //  PATIENT register and login
    private static final String LOG_IN_PATIEN = "select * from PATIENTS  where patient_email = ? AND patient_password = ?";

    private static final String UPDATE_PATIEN = "update PATIENTS set patient_name = ?, patient_email = ?, patient_password = ?, patient_phone = ?, patient_birth_date = ? where patient_id = ?";
//    private static final String DELETE_PATIEN = "delete from PATIENTS where patient_id = ?";


//    private static final String REQUEST_CONSULTATION = "INSERT INTO CONSULTATIONS (patient_id, doctor_id, status) VALUES (?, ?, 'pending')";
//    private static final String CHECK_CONSULTATION_RESULT = "SELECT * FROM CONSULTATIONS WHERE consultation_id = ?";
//    private static final String REQUEST_MEDICAL_HISTORY = "SELECT * FROM CONSULTATIONS WHERE patient_id = ? AND status = 'completed'";
//    private static final String RATE_DOCTOR = "UPDATE CONSULTATIONS SET rating = ? WHERE consultation_id = ?";

    //SEARCH FOR PATIONTS BY (ID - NAME - EMATIL - PHONE - DOB)
//    private static final String JOIN_DOCTOR_SELECT_PATIENT_BY_ID = "select * from PATIENTS join PATIENTS on DOCTORS.";

    //SELECT_ALL_PATIENT
    public ArrayList<PatientDto> selectAllPatients(PatientDto patientDto) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_PATIEN);
        ResultSet rs = st.executeQuery();
        ArrayList<PatientDto> patientDtos = new ArrayList<>();
        while (rs.next()) {
            patientDtos.add(new PatientDto(rs));
        }
        return patientDtos;
    }

//    select one patient
    public Patient selectPatient(int patient_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_PATIEN);
        st.setInt(1, patient_id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Patient(rs);
        } else {
            return null;
        }
    }


    //Register insert
    public void insertPatient(Patient p) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(INSERT_PATIENT_REGISTER);
//        st.setInt(1, p.getPatient_id());
        st.setString(1, p.getPatient_name());
        st.setString(2, p.getPatient_email());
        st.setString(3, p.getPatient_password());
        st.setString(4, p.getPatient_birth_date().toString());
        st.setString(5, p.getPatient_phone());
        st.executeUpdate();
    }

    //LOGIN
    public Patient loginPatien(String patient_email, String patient_password) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(LOG_IN_PATIEN);
        st.setString(1, patient_email);
        st.setString(2, patient_password);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Patient(rs);
        }
        return null;
    }

//    update Patient
    public void updatePatient(PatientDtoAll patientDtoAll) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(UPDATE_PATIEN);
        st.setString(1, patientDtoAll.getPatient_name());
        st.setString(2, patientDtoAll.getPatient_email());
        st.setString(3, patientDtoAll.getPatient_password());
        st.setString(4, patientDtoAll.getPatient_phone());
        st.setString(5, patientDtoAll.getPatient_birth_date().toString());
        st.setInt(6, patientDtoAll.getPatient_id());
        st.executeUpdate();
    }


//    public void requestConsultation(int patientId, int doctorId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//       Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(REQUEST_CONSULTATION);
//        st.setInt(1, patientId);
//        st.setInt(2, doctorId);
//        st.executeUpdate();
//    }
//
//    public Consultation checkConsultationResult(int consultationId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(CHECK_CONSULTATION_RESULT);
//        st.setInt(1, consultationId);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            return new Consultation(rs);
//        } else {
//            return null;
//        }
//    }
//
//    public ArrayList<Consultation> requestMedicalHistory(int patientId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(REQUEST_MEDICAL_HISTORY);
//        st.setInt(1, patientId);
//        ResultSet rs = st.executeQuery();
//        ArrayList<Consultation> consultations = new ArrayList<>();
//        while (rs.next()) {
//            consultations.add(new Consultation(rs));
//        }
//        return consultations;
//    }
//
//    public void rateDoctor(int consultationId, int rating) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//       Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(RATE_DOCTOR);
//        st.setInt(1, rating);
//        st.setInt(2, consultationId);
//        st.executeUpdate();
//    }


}
