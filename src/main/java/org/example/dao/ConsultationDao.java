package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.ConsultationDto;
import org.example.dto.ConsultationFilterDto;
import org.example.dto.RateDto;
import org.example.models.Consultation;

import java.sql.*;
import java.util.ArrayList;


public class ConsultationDao {

    // Data URL
//    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Final_ProjectV1\\FinalDataV4.db";

    private static final String SELECT_ALL_CONSULTATION = "select * from CONSULTATIONS";

    //• Patient can check a consultation result
    private static final String SELECT_ONE_CONSULTATION = "select * from CONSULTATIONS where consultation_id = ?";

    private static final String SELECT_CONSULT_WITH_DIAGNOSE = "select * from CONSULTATIONS where consultation_diagnosis = ?";

    //• Doctor check all pending consultation requests
    private static final String SELECT_CONSULT_DOCTOR_ID_PENDING_REQ = "select * from CONSULTATIONS where doctor_id = ? AND consultation_status = ?";
    private static final String SELECT_CONSULT_WITH_PENDING_REQ = "select * from CONSULTATIONS where consultation_status = ?";

    //Patient search for doctor by dynamic criteria [ratings]
//    private static final String SELECT_CONSULT_WITH_RATE = "select doctor_id, count(*) from CONSULTATIONS where consultation_rating = ?";
    private static final String SELECT_CONSULT_WITH_RATE = "select * from CONSULTATIONS where consultation_rating = ?";
    private static final String SELECT_RATE = "select DISTINCT doctor_id from CONSULTATIONS where consultation_rating = ?";

    //• Doctor give consultation to a pending request
    private static final String SELECT_CONSULT_WITH_STAT = "select * from CONSULTATIONS where consultation_status = ?";

    //1- Doctor give consultation to a pending request - 2- record a patient’s diagnosis
    //1- Patient request consultation from a selected doctor - 2- rate a doctor
    private static final String INSERT_CONSULTATION = "INSERT INTO CONSULTATIONS (doctor_id, patient_id, consultation_request_time, consultation_time, consultation_status, consultation_diagnosis, consultation_rating) VALUES (?, ?, ?, ?, ?, ?, ?)";

    //1- Patient request consultation from a selected doctor - 2- rate a doctor
    private static final String UPDATE_CONSULTATION = "update CONSULTATIONS set doctor_id = ?, patient_id = ?, consultation_request_time = ?, consultation_time = ?, consultation_status = ?, consultation_diagnosis = ?, consultation_rating = ? where consultation_id = ?";
//    private static final String DELETE_CONSULTATION = "delete from CONSULTATIONS where consultation_id = ?";


    //SELECT_ALL_CONSULTATION
//    public ArrayList<Consultation> selectAllConsultation(ConsultationDto consultationDto) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        try (Connection conn = MCPConnection.getConn();
//             PreparedStatement st = conn.prepareStatement(SELECT_ALL_CONSULTATION);
//             ResultSet rs = st.executeQuery();
//             ArrayList<Consultation> consultations = new ArrayList<>();
//        while (rs.next()) {
//            consultations.add(new Consultation(rs));
//        }
//
//        return consultations;
//    }


    //SELECT_ONE_Consultation
    public Consultation selectConsultationById(int consultation_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_CONSULTATION);
        st.setInt(1, consultation_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Consultation(rs);
        }
        else {
            return null;
        }
    }

    // select by many things

    public ArrayList<Consultation> selectAllConsults(ConsultationFilterDto filter) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st;
        if(filter.getConsultation_diagnosis() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_DIAGNOSE);
            st.setString(1, filter.getConsultation_diagnosis());
        }
        else if(filter.getDoctor_id() != null && filter.getConsultation_status() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_DOCTOR_ID_PENDING_REQ);
            st.setInt(1, filter.getDoctor_id());
            st.setString(2, filter.getConsultation_status());
        }
        else if(filter.getPendingRequest() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_PENDING_REQ);
            st.setString(1, filter.getPendingRequest().toString());
        }
        else if(filter.getConsultation_rating() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_RATE);
            st.setInt(1, filter.getConsultation_rating());
        }
        else if(filter.getConsultation_status() != null) {
            st = conn.prepareStatement(SELECT_CONSULT_WITH_STAT);
            st.setString(1, filter.getConsultation_status());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_CONSULTATION);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Consultation> consultations = new ArrayList<>();
        while (rs.next()) {
            consultations.add(new Consultation(rs));
        }

        return consultations;
    }

    //Insert Consultation
    public void InsertConsultation(Consultation consultation) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(INSERT_CONSULTATION);
        st.setInt(1, consultation.getDoctor_id());
        st.setInt(2, consultation.getPatient_id());
        st.setString(3, consultation.getConsultation_request_time().toString());
        st.setString(4, consultation.getConsultation_time().toString());
        st.setString(5, consultation.getConsultation_status());
        st.setString(6, consultation.getConsultation_diagnosis());
        st.setInt(7, consultation.getConsultation_rating());

        st.executeUpdate();
    }

    //    update Consultation
    public void updateConsultation(Consultation consultation) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(UPDATE_CONSULTATION);
        st.setInt(1, consultation.getDoctor_id());
        st.setInt(2, consultation.getPatient_id());
        st.setString(3, consultation.getConsultation_request_time().toString());
        st.setString(4, consultation.getConsultation_time().toString());
        st.setString(5, consultation.getConsultation_status());
        st.setString(6, consultation.getConsultation_diagnosis());
        st.setInt(7, consultation.getConsultation_rating());
        st.setInt(8, consultation.getConsultation_id());
        st.executeUpdate();
    }

    public ArrayList<RateDto> searchByRate(int rate) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = MCPConnection.getConn();
             PreparedStatement st = conn.prepareStatement(SELECT_RATE)) {
            st.setInt(1, rate);
            ResultSet rs = st.executeQuery();
            ArrayList<RateDto> cons = new ArrayList<>();
            while (rs.next()) {
                cons.add(new RateDto(rs));
            }
            return cons;
        }
    }


//    //    delete Consultation
//    public void deleteConsultation(int consultation_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(DELETE_CONSULTATION);
//        st.setInt(1, consultation_id);
//        st.executeUpdate();
//    }


//    public void requestConsultation(int patient_id, int doctor_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(REQUEST_CONSULTATION);
//        st.setInt(1, patient_id);
//        st.setInt(2, doctor_id);
//        st.executeUpdate();
//    }
//
//    public Consultation checkConsultationResult(int consultation_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(CHECK_CONSULTATION_RESULT);
//        st.setInt(1, consultation_id);
//        ResultSet rs = st.executeQuery();
//        if (rs.next()) {
//            return new Consultation(rs);
//        } else {
//            return null;
//        }
//    }

//    public ArrayList<Consultation> requestMedicalHistory(int patient_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(REQUEST_MEDICAL_HISTORY);
//        st.setInt(1, patient_id);
//        ResultSet rs = st.executeQuery();
//        ArrayList<Consultation> consultations = new ArrayList<>();
//        while (rs.next()) {
//            consultations.add(new Consultation(rs));
//        }
//        return consultations;
//  }
//
//    public void rateDoctor(int consultation_id, int consultation_rating) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(RATE_DOCTOR);
//        st.setInt(1, consultation_rating);
//        st.setInt(2, consultation_id);
//        st.executeUpdate();
//    }

}
