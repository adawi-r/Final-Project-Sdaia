package org.example.dao;

import org.example.db.MCPConnection;
import org.example.dto.DoctorFilterDto;
import org.example.models.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DoctorDao {

    // Data URL
//    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Final_ProjectV1\\FinalDataV4.db";

    //Requirements
    private static final String SELECT_ALL_DOCTOR = "select * from DOCTORS";

    //Patient search for doctor by dynamic criteria [specialties,id, name]
    private static final String SELECT_ONE_DOCTOR = "select * from DOCTORS where doctor_id = ?";
    private static final String SELECT_ONE_BY_NAME = "select * from DOCTORS where doctor_name = ?";
    private static final String SELECT_ONE_BY_SPECIALTY = "select * from DOCTORS where doctor_specialty = ?";

    //• Doctor register and login
    private static final String INSERT_DOCTOR_REGISTER = "insert into DOCTORS ( doctor_name, doctor_specialty, doctor_email, doctor_password, doctor_phone) values (?, ?, ?, ?, ?)";
    private static final String LOG_IN_DOCTOR = "select * from DOCTORS where doctor_email = ? AND doctor_password = ?";

    private static final String UPDATE_DOCTOR = "update DOCTORS set doctor_name = ?, doctor_specialty = ?, doctor_email = ?, doctor_password = ?, doctor_phone = ? where doctor_id = ?";
//    private static final String DELETE_DOCTOR = "delete from DOCTORS  where doctor_id = ?";
    private static final String SCHEDULE_AVAILABILITY_BY_DOCTOR = "SELECT doctor_id, doctor_name, doctor_specialty FROM DOCTORS WHERE doctor_id in (select distinct (doctor_id) from SCHEDULES where SCHEDULES.schedule_is_available = ?)";



//    //SELECT_ALL_DOCTOR
//    public ArrayList<Doctor> selectAllDoctor(DoctorDto doctorDto) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_ALL_DOCTOR);
//        ResultSet rs = st.executeQuery();
//        ArrayList<Doctor> doctors = new ArrayList<>();
//        while (rs.next()) {
//            doctors.add(new Doctor(rs));
//        }
//
//        return doctors;
//    }
//

// SELECT_ONE_DOCTOR
    public Doctor selectDoctorById(int doctor_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_DOCTOR);
        st.setInt(1, doctor_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Doctor(rs);
        }
        else {
            return null;
        }
    }

// SELECT MANY THING

    public ArrayList<Doctor> selectAllDocs(DoctorFilterDto filter) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st ;
        if(filter.getDoctor_id() != null ) {
            st = conn.prepareStatement(SELECT_ONE_DOCTOR);
            st.setInt(1, filter.getDoctor_id());
        }
        else if(filter.getDoctor_name() != null) {
            st = conn.prepareStatement(SELECT_ONE_BY_NAME);
            st.setString(1,filter.getDoctor_name());
        }
        else if(filter.getDoctor_specialty() != null) {
            st = conn.prepareStatement(SELECT_ONE_BY_SPECIALTY);
            st.setString(1, filter.getDoctor_specialty());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_DOCTOR);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Doctor> docs = new ArrayList<>();
        while (rs.next()) {
            docs.add(new Doctor(rs));
        }

        return docs;
    }


    //REGISTER NEW DOCTOR
    public void registerDoctor(Doctor d) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(INSERT_DOCTOR_REGISTER);
//        st.setInt(1, d.getDoctor_id());
        st.setString(1, d.getDoctor_name());
        st.setString(2, d.getDoctor_specialty());
        st.setString(3, d.getDoctor_email());
        st.setString(4, d.getDoctor_password());
        st.setString(5, d.getDoctor_phone());

        st.executeUpdate();
    }

    //DOCTOR LOGIN
    public Doctor loginDoctor(String doctor_email, String doctor_password) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(LOG_IN_DOCTOR);
        st.setString(1, doctor_email);
        st.setString(2, doctor_password);

        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Doctor(rs);
        }
        return null;
    }

//    update
    public void updateDoctor(Doctor d) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(UPDATE_DOCTOR);
        st.setString(1, d.getDoctor_name());
        st.setString(2, d.getDoctor_specialty());
        st.setString(3, d.getDoctor_email());
        st.setString(4, d.getDoctor_password());
        st.setString(5, d.getDoctor_phone());
        st.setInt(6, d.getDoctor_id());
        st.executeUpdate();
    }


    // doctor info based on availablity
    public ArrayList<Doctor> getAvailableDoctors(boolean schedule_is_available) throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> doctors = new ArrayList<>();

        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SCHEDULE_AVAILABILITY_BY_DOCTOR);

        st.setBoolean(1, schedule_is_available);

        try(ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("doctor_specialty")
                );
                doctors.add(doctor);
            }
        }

        return doctors;
    }

    // doctor info based on availablity
//    public ArrayList<Doctor> getAvailableDoctors(boolean schedule_is_available) throws SQLException, ClassNotFoundException {
//        ArrayList<Doctor> doctors = new ArrayList<>();
//
//        Connection conn = MCPConnection.getConn();
//        PreparedStatement st = conn.prepareStatement(SCHEDULE_AVAILABILITY_BY_DOCTOR);
////        st.setBoolean(1, schedule_is_available);
//
//            try(ResultSet resultSet = st.executeQuery()) {
//                while (resultSet.next()) {
//                    int doctor_id = resultSet.getInt("doctor_id");
//                    String doctor_name = resultSet.getString("doctor_name");
//                    String doctor_specialty = resultSet.getString("doctor_specialty");
//
//                    Doctor doctor = new Doctor(doctor_id, doctor_name, doctor_specialty);
//                    doctors.add(doctor);
//                }
//            }
//
//        return doctors;
//    }

//    //MANAAGE AVAILABILITY SCHEDULE
//    public void addSchedule(Schedule s) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//Connection conn = MCPConnection.getConn();
    //        PreparedStatement st = conn.prepareStatement(INSERT_DOCTOR_SCHEDULE);
//        st.setInt(1, s.getDoctor_id());
//        st.setString(2, s.getSchedule_start_time().toString());
//        st.setString(2, s.getSchedule_end_time().toString());
//        st.setBoolean(3,s.getSchedule_is_available());
//
//        st.executeUpdate();
//    }
//
//    //CHECK PENDING CONSULTAION REQUESTS
//    public ArrayList<Consultation> getPendingConsultaion(int doctor_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        ArrayList<Consultation> consultations = new ArrayList<>();
//        Connection conn = DriverManager.getConnection(URL);
//
//        PreparedStatement st = conn.prepareStatement(SELECT_PENDING_CONSULTATIONS);
//        st.setInt(1, doctor_id);
//
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            consultations.add(new Consultation(rs));
//        }
//        return consultations;
//    }
//
//    //GIVE CONSULTAION FOR PENDING REQUESTS AND RECORD DIAGNOSIS AND RATING
//    public void recordConsultaion(Consultation c) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(UPDATE_CONSULTATIONS);
//        st.setString(1, c.getConsultation_time().toString());
//        st.setString(2, c.getConsultation_status());
//        st.setString(3, c.getConsultation_diagnosis());
//        st.setInt(4, c.getConsultation_rating());
//        st.setInt(5,c.getConsultation_id());
//
//        st.executeUpdate();
//    }
//
//    //SEARCH PATIENT MEDICAL RECORDS
//    public ArrayList<Consultation> searchPatientRecord(int patient_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        ArrayList<Consultation> consultations = new ArrayList<>();
//        Connection conn = DriverManager.getConnection(URL);
//
//        PreparedStatement st = conn.prepareStatement(SELECT_PATIENT_RECORDS);
//        st.setInt(1, patient_id);
//
//        ResultSet rs = st.executeQuery();
//        while (rs.next()) {
//            consultations.add(new Consultation(rs));
//        }
//        return consultations;
//    }

}
