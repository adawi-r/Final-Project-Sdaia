package org.example.dao;

import org.example.dto.MedicalReporFilterDto;
import org.example.models.MedicalReport;
import org.example.db.MCPConnection;

import java.sql.*;
import java.util.ArrayList;

public class MedicalReportDao {

    // Data URL
//    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Final_ProjectV1\\FinalDataV4.db";

    private static final String SELECT_ALL_MEDICAL_REPORT = "select * from MEDICAL_REPORTS";
    private static final String SELECT_ONE_MEDICAL_REPORT = "select * from MEDICAL_REPORTS where medical_report_id = ?";

    //• Doctor search patients’ medical records
    private static final String SELECT_MEDICAL_REPORTS_BY_PATIENT = "SELECT * FROM MEDICAL_REPORTS WHERE patient_id = ?";

    //• Patient can request a medical history report for all previously recorded diagnosis
    private static final String SELECT_MEDICAL_REPORT_BY_DETAILS= "select * from MEDICAL_REPORTS where medical_report_details = ?";

    private static final String INSERT_MEDICAL_REPORT = "INSERT INTO MEDICAL_REPORTS (patient_id, medical_report_details, medical_report_report_date) VALUES (?, ?, ?)";

    private static final String UPDATE_MEDICAL_REPORT = "update MEDICAL_REPORTS set patient_id = ?, medical_report_details = ? , medical_report_report_date = ? where medical_report_id = ?";
//    private static final String DELETE_MEDICAL_REPORT = "DELETE FROM MEDICAL_REPORTS WHERE medical_report_id = ?";



    //SELECT_ALL_MEDICAL_REPORTS
//    public ArrayList<MedicalReport> selectAllMEDICAL_REPORTS(MedicalReportDto medicalReportDto) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_ALL_MEDICAL_REPORT);
//        ResultSet rs = st.executeQuery();
//        ArrayList<MedicalReport> medicalReports = new ArrayList<>();
//        while (rs.next()) {
//            medicalReports.add(new MedicalReport(rs));
//        }
//
//        return medicalReports;
//    }


    //SELECT_ONE_MEDICAL_REPORTS
    public MedicalReport selectMedicalReportById(int medical_report_id) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_MEDICAL_REPORT);
        st.setInt(1, medical_report_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new MedicalReport(rs);
        }
        else {
            return null;
        }
    }

    // select many things
    public ArrayList<MedicalReport> selectAllMeds(MedicalReporFilterDto filter) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st;
        if(filter.getPatient_id() != null) {
            st = conn.prepareStatement(SELECT_MEDICAL_REPORTS_BY_PATIENT);
            st.setInt(1, filter.getPatient_id());
        }
        else if(filter.getMedical_report_details() != null) {
            st = conn.prepareStatement(SELECT_MEDICAL_REPORT_BY_DETAILS);
            st.setString(1, filter.getMedical_report_details());
        }else {
            st = conn.prepareStatement(SELECT_ALL_MEDICAL_REPORT);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<MedicalReport> medicalReports = new ArrayList<>();
        while (rs.next()) {
            medicalReports.add(new MedicalReport(rs));
        }
        return medicalReports;
    }


//• Doctor search patients’ medical records
//INSERT_MEDICAL_REPORT
    public void InsertMedicalReport(MedicalReport medicalReport) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(INSERT_MEDICAL_REPORT);

        st.setInt(1, medicalReport.getPatient_id());
        st.setString(2, medicalReport.getMedical_report_details());
        st.setString(3, medicalReport.getMedical_report_report_date().toString());
        st.executeUpdate();
    }

    //    update MedicalReport
    public void updateMedicalReport(MedicalReport medicalReport) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
        Connection conn = MCPConnection.getConn();
        PreparedStatement st = conn.prepareStatement(UPDATE_MEDICAL_REPORT);
        st.setInt(1, medicalReport.getPatient_id());
        st.setString(2, medicalReport.getMedical_report_details());
        st.setString(3, medicalReport.getMedical_report_report_date().toString());
        st.setInt(4, medicalReport.getMedical_report_id());

        st.executeUpdate();
    }


//
//    public ArrayList<MedicalReport> getMedicalReportsByPatient(int patientId) throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        Connection conn = DriverManager.getConnection(URL);
//        PreparedStatement st = conn.prepareStatement(SELECT_MEDICAL_REPORTS_BY_PATIENT);
//        st.setInt(1, patientId);
//        ResultSet rs = st.executeQuery();
//        ArrayList<MedicalReport> reports = new ArrayList<>();
//        while (rs.next()) {
//            reports.add(new MedicalReport(rs));
//        }
//        return reports;
//    }

}
